package com.cookandroid.swu.Fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;


import com.cookandroid.swu.AdapterPlistTime;
import com.cookandroid.swu.ListViewItemPlistTime;
import com.cookandroid.swu.PlistActivity;
import com.cookandroid.swu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PlistFragment extends Fragment{
    ListView lvPlist;
    FloatingActionButton fabAdd;
//    final int REQUESTCODE_REVIEW_WRITE = 3;
    public static AdapterPlistTime plAdapter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plist, container, false);

        fabAdd = view.findViewById(R.id.fabAdd);
        lvPlist = view.findViewById(R.id.lvPlist);

        plAdapter = new AdapterPlistTime();
        lvPlist.setAdapter(plAdapter);

        // 복용약 리스트 추가하기
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlistActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        return view;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public static void addItem(Bitmap icon, String name, String t1, String t2, String t3, String t4, String t5, String t6) {
        plAdapter.addItem(icon, name, t1, t2, t3, t4, t5, t6);
        plAdapter.notifyDataSetChanged();
    }
}
