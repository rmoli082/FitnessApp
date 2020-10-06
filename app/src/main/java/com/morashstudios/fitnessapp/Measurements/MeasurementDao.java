package com.morashstudios.fitnessapp.Measurements;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MeasurementDao {

    @Insert
    void insert(Measurement measurement);

    @Query("SELECT * FROM measurements")
    LiveData<List<Measurement>> getAllMeasurements();

    @Delete
    void delete(Measurement measurement);
}
