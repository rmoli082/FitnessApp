package com.morashstudios.fitnessapp.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MeasurementsRepository {

    private MeasurementsDao mMeasurementsDao;
    private LiveData<List<Measurements>> mAllMeasurements;

    MeasurementsRepository(Application application) {
        MeasurementsDatabase db = MeasurementsDatabase.getInstance(application);
        mMeasurementsDao = db.getMeasurementsDao();
        mAllMeasurements = mMeasurementsDao.getAll();
    }

    LiveData<List<Measurements>> getAllMeasurements() {
        return mAllMeasurements;
    }

    void insert(Measurements measurements) {
        MeasurementsDatabase.databaseWriteExecutor.execute(() -> {
            mMeasurementsDao.insert(measurements);
        });
    }
}
