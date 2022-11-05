package com.cookandroid.swu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AdapterPlistAlertTime extends ArrayAdapter implements View.OnClickListener {


    // 버튼 클릭 이벤트를 위한 Listener 인터페이스 정의
    public interface ListBtnClickListener {
        void onListBtnClick(int position);
    }

    // 생성자로부터 전달된 resource id 값을 저장
    int resourceId;
    // 생성자로부터 전달된 listBtnClickListener 저장
    private ListBtnClickListener listBtnClickListener;

    // AdapterPlistAlertTime 생성자. 마지막에 ListBtnClickListener 추가

    public AdapterPlistAlertTime(@NonNull Context context, int resource,
                                 ArrayList<ListViewItemPlistAlertTime> list, ListBtnClickListener clickListener) {
        super(context, resource, list);
        // resource id 값 복사 (super로 전달된 resource를 참조할 방법이 없음)
        this.resourceId = resource;
        this.listBtnClickListener = clickListener;
    }

    // 새롭게 만든 Layout을 위한 View를 생성하는 코드
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // 생성자로부터 저장된 resourceId(listview_plist)에 해당하는 Layout을 inflate하여 convertView 참조 획득
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resourceId/*R.layout.listview_plist*/, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)로부터 위젯에 대한 참조 획득
        final TextView txtTitle = convertView.findViewById(R.id.txtTitle);
        final TextView txtTitleNum = convertView.findViewById(R.id.txtTitleNum);
        final TextView txtTime = convertView.findViewById(R.id.txtTime);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        final ListViewItemPlistAlertTime listViewItem = (ListViewItemPlistAlertTime) getItem(position);

        // 아이템 내 각 위젯에 데이터 반영
        txtTitle.setText(listViewItem.getTitle());
        txtTitleNum.setText(listViewItem.getTitleNum());
        txtTime.setText(listViewItem.getTime());

        // 버튼(btnTimePick) TAG에 position 값 지정. Adapter를 click Listener로 지정
        Button btnTimePick = convertView.findViewById(R.id.btnTimePick);
        btnTimePick.setTag(position);
        btnTimePick.setOnClickListener(this);

        return convertView;
    }

    // btnTimePick이 눌렸을 때 실행되는 onClick 함수
    @Override
    public void onClick(View view) {
        // ListBtnClickListener(MainActivity)의 onListBtnClick() 함수 호출
        if(this.listBtnClickListener != null)
            this.listBtnClickListener.onListBtnClick((int)view.getTag());
    }

}
