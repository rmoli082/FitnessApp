package com.morashstudios.fitnessapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Measurements.class}, version = 1, exportSchema = false)
public abstract class MeasurementsDatabase extends RoomDatabase {

    public abstract MeasurementsDao getMeasurementsDao();
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static MeasurementsDatabase measurementsDB;

    static MeasurementsDatabase getInstance(final Context context) {
        if (measurementsDB == null) {
            synchronized (MeasurementsDatabase.class) {
                if (measurementsDB == null) {
                    measurementsDB = Room.databaseBuilder(context.getApplicationContext(),
                            MeasurementsDatabase.class, "measurements_table").build();
                }
            }
        }
        return measurementsDB;
    }
}
