package com.cookandroid.swu;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class RecomMainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    Button[] recomBtn = new Button[8];
    Button result_recombtn;
    private String recom_id;

    private String chooserecom = null;
    //TextView textrecom;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recom_main);

        ActionBar ac=getSupportActionBar();
        ac.setDisplayHomeAsUpEnabled(true);
        ac.setTitle("증상으로 약 추천"); //actionbar추가

        //증상 버튼 이벤트 실행
        settingRecombtn();
    }

    //증상 버튼 이벤트
    public void settingRecombtn(){
        for(int i=0; i <recomBtn.length; i++){
            recom_id = "recom_btn" + (i+1); //버튼 아이디값 저장
            recomBtn[i] = findViewById(getResources().getIdentifier(recom_id, "id",getPackageName())); //버튼 초기화
        }

        for(Button buttonId : recomBtn){
            buttonId.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    result_recombtn = findViewById(v.getId());
                    //result_recombtn.setBackgroundResource(R.drawable.choose_btton); //해당아이디 버튼의 배경색을 바꿈
                    //result_recombtn.setTextColor(Color.WHITE);
                    chooserecom = result_recombtn.getText().toString(); //선택 증상을 저장

                    //////여기서 for문으로 thiscolor랑 result.getText.toString()비교해서 배경색 다시 바꿔주기
                    Log.e("다음 클릭 후 : ", chooserecom);

                    Intent intent = new Intent(getApplicationContext(), RecomSearchActivity.class);
                    intent.putExtra("chooserecom",chooserecom);

                    Log.e("길이 : ", String.valueOf(chooserecom.length()));
                    startActivity(intent);
                }
            });
        }
    }

}