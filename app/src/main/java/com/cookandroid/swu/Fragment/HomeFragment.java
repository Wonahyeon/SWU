package com.cookandroid.swu.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cookandroid.swu.ListViewAdapter;
import com.cookandroid.swu.MainActivity;
import com.cookandroid.swu.PlistActivity;
import com.cookandroid.swu.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment {
    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("약굿타임");
    }

    Context context;
    Button Yes,No;
    public MaterialCalendarView materialCalendarView;
    public TextView PName,EName;

    ListView customListView;
    private static ListViewAdapter listViewAdapter= new ListViewAdapter();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstancedState) {
        super.onViewCreated(view, savedInstancedState);
        EName = view.findViewById(R.id.EName);
        PName = view.findViewById(R.id.PName);
        customListView = (ListView) view.findViewById(R.id.listView_custom);
        customListView.setAdapter(listViewAdapter);


        materialCalendarView = view.findViewById(R.id.calendarView);
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2022,10,1))
                .setMaximumDate(CalendarDay.from(2032,11,31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        materialCalendarView.setSelectedDate(CalendarDay.today());
        materialCalendarView.addDecorators(
                new HomeFragment.SundayDecorator(),
                new HomeFragment.SaturdayDecorator(),
                new HomeFragment.OneDayDecorator());
        materialCalendarView.setTitleFormatter(new TitleFormatter() {
            @Override
            public CharSequence format(CalendarDay day) {
                Date inputText = day.getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String getTime = sdf.format(inputText);

                String[] calendarHeaderElements = getTime.split("-");
                StringBuilder calendarHeaderBuilder = new StringBuilder();
                calendarHeaderBuilder.append(calendarHeaderElements[0])
                        .append("년")
                        .append(" ")
                        .append(calendarHeaderElements[1])
                        .append("월");
                return calendarHeaderBuilder.toString();
            }
        });


        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int year = date.getYear();
                int month = date.getMonth()+1;
                int day = date.getDay();
                PName.setText(year + "년" + month +"월" + day + "일");
                EName.setText(year + "년" + month +"월" + day + "일");
            }
        });


        context = ((MainActivity) getActivity()).getApplicationContext();
        view.findViewById(R.id.imgBin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog homedia;
                homedia = new Dialog(view.getContext());
                homedia.setContentView(R.layout.activity_home_del);
                homedia.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                homedia.show();

                Yes = (Button) homedia.findViewById(R.id.Yes);
                No = (Button) homedia.findViewById(R.id.No);

                Yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "확인되었습니다.", Toast.LENGTH_SHORT).show();
                        homedia.dismiss();
                    }
                });

                No.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, "우리집 구급함에서 확인해주세요.", Toast.LENGTH_SHORT).show();
                        homedia.dismiss();
                    }
                });

            }
        });

        view.findViewById(R.id.drugTime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //먹는 시간 기입을 위해서 plus_pill 로 이동
                Intent intent = new Intent(getActivity(), PlistActivity.class);
                startActivity(intent);
            }
        });
    }


    private class SundayDecorator implements DayViewDecorator {
        private final Calendar calendar = Calendar.getInstance();

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            day.copyTo(calendar);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            return weekDay == Calendar.SUNDAY;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(Color.RED));

        }
    }

    private class SaturdayDecorator implements DayViewDecorator {
        private final Calendar calendar = Calendar.getInstance();
        @Override
        public boolean shouldDecorate(CalendarDay day) {
            day.copyTo(calendar);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            return weekDay == Calendar.SATURDAY;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(Color.BLUE));
        }
    }

    public class OneDayDecorator implements DayViewDecorator {
        private CalendarDay date;
        public OneDayDecorator(){
            date = CalendarDay.today();
        }
        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return date != null && day.equals(date);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new StyleSpan(Typeface.BOLD));
            view.addSpan(new RelativeSizeSpan(1.4f));
        }
        public void setDate(Date date){
            this.date = CalendarDay.from(date);
        }
    }

}