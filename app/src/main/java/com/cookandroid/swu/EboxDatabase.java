package com.cookandroid.swu;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Ebox.class},version =1)

public abstract class EboxDatabase extends RoomDatabase {
    private static EboxDatabase INSTANCE;
    public abstract EboxDao eboxDao();

    public static EboxDatabase getAppDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context,EboxDatabase.class,"ebox-db").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
