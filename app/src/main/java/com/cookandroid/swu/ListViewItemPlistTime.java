package com.cookandroid.swu;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class ListViewItemPlistTime {
    Bitmap plItembitmap;
    String plItemname;
    String plItemDay;
    String plMemo;

    public void setBitmap(Bitmap bitmap) {
        this.plItembitmap = bitmap;
    }
    public void setName(String name) {
        this.plItemname = name;
    }
    public void setDay(String day) { this.plItemDay = day; }
    public void setMemo(String memo) {this.plMemo = memo;}


    public Bitmap getBitmap(){
        return this.plItembitmap;
    }
    public String getName(){
        return this.plItemname;
    }
    public String getDay(){
        return this.plItemDay;
    }
    public String getMemo() {return this.plMemo;}
}
