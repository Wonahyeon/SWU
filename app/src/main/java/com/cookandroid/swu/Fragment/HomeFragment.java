package com.cookandroid.swu.Fragment;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.cookandroid.swu.MainActivity;
import com.cookandroid.swu.R;

public class HomeFragment extends Fragment {
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstancedState){
        super.onViewCreated(view,savedInstancedState);
        context = ((MainActivity)getActivity()).getApplicationContext();
        view.findViewById(R.id.ImgBin).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                show();
            }
        });
    }
    void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("우리집 구급함");
        builder.setMessage("약을 버렸나요?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context,"확인되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Toast.makeText(context,"우리집 구급함으로 이동합니다.",Toast.LENGTH_SHORT).show();
                // 우리집 구급함으로 이동
            }
        });
        builder.show();
    }



}
