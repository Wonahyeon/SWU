package com.cookandroid.swu.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.cookandroid.swu.EboxAdd;
import com.cookandroid.swu.ListViewAdapter;
import com.cookandroid.swu.R;

import java.util.List;


public class EboxFragment extends Fragment {
Button addbutton;
ListView customListView;
private static ListViewAdapter listViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ebox, container, false);

        customListView = (ListView) v.findViewById(R.id.listView_custom);
        listViewAdapter = new ListViewAdapter();
        customListView.setAdapter(listViewAdapter);

        addbutton =(Button) v.findViewById(R.id.addbtn);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EboxAdd.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        return v;
    }
    public static void addItem(Bitmap icon, String title, String desc) {
        listViewAdapter.addItem(icon, title, desc) ;
        listViewAdapter.notifyDataSetChanged();
    }
}