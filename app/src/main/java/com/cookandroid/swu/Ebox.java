package com.cookandroid.swu;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Ebox {
    @PrimaryKey(autoGenerate = true) //값 자동으로 생성
    public int id;

    @ColumnInfo(name = "ename")
    public String ename;

    @ColumnInfo(name = "ememo")
    public String ememo;
}
