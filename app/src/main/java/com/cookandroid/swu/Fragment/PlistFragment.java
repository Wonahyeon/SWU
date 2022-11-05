package com.cookandroid.swu.Fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.cookandroid.swu.PlistActivity;
import com.cookandroid.swu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PlistFragment extends Fragment implements View.OnClickListener{
    ListView lvPlist;
    FloatingActionButton fabAdd;
    String name, memo, day;
    Integer listCount;
    RelativeLayout topLayout;
    Bitmap bitmap;
    public int REQUESTCODE_REVIEW_WRITE= 3;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plist, container, false);

        fabAdd = view.findViewById(R.id.fabAdd);
        lvPlist = view.findViewById(R.id.lvPlist);
        topLayout = view.findViewById(R.id.topLayout);


        // 복용약 리스트 추가하기
        fabAdd.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fabAdd:
                Intent intent = new Intent(getActivity(), PlistActivity.class);
//                startActivity(intent);
                startActivityForResult(intent, REQUESTCODE_REVIEW_WRITE);
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUESTCODE_REVIEW_WRITE) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = getArguments();
                if (bundle != null) {
                    name = bundle.getString("name");
                    memo = bundle.getString("memo");
                    day = bundle.getString("day");
                    listCount = bundle.getInt("listCount");
//                    bitmap = extra.getParcelable("bitmap");
                }
                else {
                    Toast.makeText(getActivity(), name + memo + day, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
