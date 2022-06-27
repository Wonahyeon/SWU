package com.cookandroid.swu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.cookandroid.swu.Fragment.EboxFragment;
import com.cookandroid.swu.Fragment.HomeFragment;
import com.cookandroid.swu.Fragment.PlistFragment;
import com.cookandroid.swu.Fragment.SearchFragment;
import com.cookandroid.swu.Fragment.SetFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    Fragment HomeFragment,SearchFragment,EboxFragment,PlistFragment,SetFragment;
    private final long finishTime = 1000;
    private long pressTime = 0;
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
    }


    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - pressTime;
        if (0 <= intervalTime && finishTime >= intervalTime){
            finish();
        }
        else {
            pressTime = tempTime;
            Toast.makeText(getApplicationContext(),"한번더 누르시면 앱이 종료됩니다.",Toast.LENGTH_SHORT).show();
        }
    }
}