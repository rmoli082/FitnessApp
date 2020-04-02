package com.morashstudios.fitnessapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MeasurementViewModel extends AndroidViewModel {

    private MeasurementDao measurementDao;
    private MeasurementDatabase measurementDB;
    private LiveData<List<Measurement>> mAllMeasurements;

    public MeasurementViewModel(Application application) {

        super(application);

        measurementDB = MeasurementDatabase.getDatabase(application);
        measurementDao = measurementDB.measurementDao();
        mAllMeasurements = measurementDao.getAllMeasurements();
    }

    public void insert(Measurement measurement) {
        new InsertAsyncTask(measurementDao).execute(measurement);
    }

    public void delete(Measurement measurement) {
        new DeleteAsyncTask(measurementDao).execute(measurement);
    }

    LiveData<List<Measurement>> getAllMeasurements() {
        return mAllMeasurements;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    private class OperationsAsyncTask extends AsyncTask<Measurement, Void, Void> {
        MeasurementDao mAsyncTaskDao;

        OperationsAsyncTask(MeasurementDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Measurement... measurements) {
            return null;
        }
    }
    private class InsertAsyncTask extends OperationsAsyncTask {

        public InsertAsyncTask(MeasurementDao mMeasurementDao) {
            super(mMeasurementDao);
        }

        @Override
        protected Void doInBackground(Measurement... measurements) {
            mAsyncTaskDao.insert(measurements[0]);
            return null;
        }
    }

    private class DeleteAsyncTask extends OperationsAsyncTask {

        public DeleteAsyncTask(MeasurementDao measurementDao) {
            super(measurementDao);
        }

        @Override
        protected Void doInBackground(Measurement... measurements) {
            mAsyncTaskDao.delete(measurements[0]);
            return null;
        }
    }
}
