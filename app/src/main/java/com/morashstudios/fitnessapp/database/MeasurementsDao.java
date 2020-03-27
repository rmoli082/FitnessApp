package com.morashstudios.fitnessapp.database;

import android.icu.util.Measure;
import android.provider.SyncStateContract;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MeasurementsDao {
    @Query("SELECT * FROM measurements_table")
    LiveData<List<Measurements>> getAll();

    @Insert
    void insert(Measurements measurements);

    @Update
    void update(Measurements measurements);

    @Delete
    void delete(Measurements measurements);

    @Delete
    void delete(Measurements... measurements);

}
