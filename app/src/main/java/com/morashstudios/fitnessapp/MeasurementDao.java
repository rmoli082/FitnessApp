package com.morashstudios.fitnessapp;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface MeasurementDao {

    @Insert
    void insert(Measurement measurement);
}
