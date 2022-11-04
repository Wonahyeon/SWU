package com.cookandroid.swu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;

import java.util.Calendar;

public class EboxAdd extends AppCompatActivity {

    private ImageView view1;
    private Button dateview;

    //날짜 선택
    private Calendar c = Calendar.getInstance();
    int mYear = c.get(Calendar.YEAR);
    int mMonth= c.get(Calendar.MONTH);
    int mDay = c.get(Calendar.DAY_OF_MONTH);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebox_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //뒤로가기 버튼

        view1 = (ImageView) findViewById(R.id.ebox_view);
        dateview = (Button) findViewById(R.id.datebtn);

        //camera
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        //doTakePhotoAction();
                    }
                };
                DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        // doTakeAlbumAction();
                    }
                };
                DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                };
                new AlertDialog.Builder(EboxAdd.this)
                        .setTitle("이미지를 선택하세요")
                        .setPositiveButton("사진촬영", cameraListener)
                        .setNeutralButton("앨범선택", albumListener)
                        .setNegativeButton("취소", cancelListener)
                        .show();
            }

        });

        //Date
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateview.setText(year + " - " + (month + 1) + " - " + dayOfMonth);
            }
        }, mYear, mMonth, mDay);

        dateview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dateview.isClickable()) {
                    datePickerDialog.getDatePicker().setCalendarViewShown(false);
                    datePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    datePickerDialog.show();
                }
            }
        }); //setonclicklistener
    }//oncreate
}//class