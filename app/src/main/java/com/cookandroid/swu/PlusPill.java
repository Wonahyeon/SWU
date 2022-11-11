package com.cookandroid.swu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.cookandroid.swu.Fragment.EboxFragment;
import com.cookandroid.swu.Fragment.PlistFragment;

public class PlusPill extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus_pill);
    }

}