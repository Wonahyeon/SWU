package com.cookandroid.swu.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cookandroid.swu.ListViewAdapter;
import com.cookandroid.swu.MainActivity;
import com.cookandroid.swu.PlistActivity;
import com.cookandroid.swu.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

import static androidx.navigation.Navigation.createNavigateOnClickListener;
import static androidx.navigation.Navigation.findNavController;

public class HomeFragment extends Fragment {
    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("약굿타임");
    }

    Context context;
    ListView plistItem, eboxItem;
    ArrayAdapter<String> adapter;
    ArrayList<String> listItem;
    Button Yes,No;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstancedState) {
        super.onViewCreated(view, savedInstancedState);
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






}