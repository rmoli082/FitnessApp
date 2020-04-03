package com.morashstudios.fitnessapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;

public class MeasurementViewModel extends AndroidViewModel {

    private MeasurementDao measurementDao;
    private MeasurementDatabase measurementDB;

    public MeasurementViewModel(Application application) {

        super(application);

        measurementDB = MeasurementDatabase.getDatabase(application);
        measurementDao = measurementDB.measurementDao();
    }

    public void insert(Measurement measurement) {
        new InsertAsyncTask(measurementDao).execute(measurement);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    private class InsertAsyncTask extends AsyncTask<Measurement, Void, Void> {

        MeasurementDao mMeasurementDao;

        public InsertAsyncTask(MeasurementDao mMeasurementDao) {
            this.mMeasurementDao = mMeasurementDao;
        }

        @Override
        protected Void doInBackground(Measurement... measurements) {
            mMeasurementDao.insert(measurements[0]);
            return null;
        }
    }
}
