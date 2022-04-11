package com.cookandroid.swu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.cookandroid.swu.Fragment.EboxFragment;
import com.cookandroid.swu.Fragment.HomeFragment;
import com.cookandroid.swu.Fragment.PlistFragment;
import com.cookandroid.swu.Fragment.SearchFragment;
import com.cookandroid.swu.Fragment.SetFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    Fragment HomeFragment;
    Fragment SearchFragment;
    Fragment EboxFragment;
    Fragment PlistFragment;
    Fragment SetFragment;
    ImageButton ImgBin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeFragment = new HomeFragment();
        SearchFragment = new SearchFragment();
        EboxFragment = new EboxFragment();
        PlistFragment = new PlistFragment();
        SetFragment = new SetFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,HomeFragment).commit();
        //네비게이션바
        NavigationBarView navigationBarView = findViewById(R.id.bottom_navigation);
        navigationBarView.setOnItemSelectedListener(new  NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.nav_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,HomeFragment).commit();
                        return true;
                    case R.id.nav_search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, SearchFragment).commit();
                        return true;
                    case R.id.nav_ebox:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, EboxFragment).commit();
                        return true;
                    case R.id.nav_plist:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, PlistFragment).commit();
                        return true;
                    case R.id.nav_set:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, SetFragment).commit();
                        return true;
                }
                return false;
            }
        });
        //팝업창
        ImgBin = findViewById(R.id.ImgBin);
        ImgBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //팝업 알림 생성
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);

                dlg.setTitle("우리집 구급함");    //제목
                dlg.setMessage("유통기한 지난 약이 존재합니다. 버리셨나요?"); //메시지
                //dlg.setIcon(R.drawable.ebox);   //아이콘 설정

                //클릭시 동작
                //버튼 설정
                //ok 눌렀을 때
                dlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 토스트 메시지
                        Toast.makeText(MainActivity.this,"확인되었습니다.",Toast.LENGTH_SHORT).show();
                    }
                    //no 눌렀을 때
                });
                //dlg.setCancelable(false);   //팝업 떴을 때 뒤로가기 버튼 x
                dlg.show(); //팝업 띄우기
            }
        });



    }
}