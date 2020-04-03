package com.morashstudios.fitnessapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Measurement.class, version = 1)
public abstract class MeasurementDatabase extends RoomDatabase {

    public abstract MeasurementDao measurementDao();

    private static volatile MeasurementDatabase measurementDbInstance;

    static MeasurementDatabase getDatabase(final Context context) {
        if (measurementDbInstance == null) {
            synchronized (MeasurementDatabase.class) {
                if (measurementDbInstance == null) {
                    measurementDbInstance = Room.databaseBuilder(context.getApplicationContext(),
                            MeasurementDatabase.class, "measurement_database").build();
                }
            }
        }
        return measurementDbInstance;
    }
}
