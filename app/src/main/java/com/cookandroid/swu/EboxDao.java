package com.cookandroid.swu;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface EboxDao {

        @Query("SELECT * FROM ebox")
        List<Ebox> getAll();

        @Query("SELECT ename FROM ebox")
        List<String> geteEnameAll();

        @Query("SELECT ememo FROM ebox")
        List<String> getEmemoAll();

        @Insert
        void insertAll(Ebox... eboxes);

        @Delete
        void delete(Ebox ebox);

}
