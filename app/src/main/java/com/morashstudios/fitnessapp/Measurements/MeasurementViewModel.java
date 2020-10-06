package com.morashstudios.fitnessapp.Measurements;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MeasurementViewModel extends AndroidViewModel {

    private final MeasurementDao measurementDao;
    private final LiveData<List<Measurement>> mAllMeasurements;

    public MeasurementViewModel(Application application) {

        super(application);

        MeasurementDatabase measurementDB = MeasurementDatabase.getDatabase(application);
        measurementDao = measurementDB.measurementDao();
        mAllMeasurements = measurementDao.getAllMeasurements();
    }

    public MeasurementViewModel(Application application, MeasurementDao measurementDao, LiveData<List<Measurement>> allMeasurements) {

        super(application);

        MeasurementDatabase measurementDB = MeasurementDatabase.getDatabase(application);
        this.measurementDao = measurementDao;
        mAllMeasurements = allMeasurements;
    }

    public void insert(Measurement measurement) {
        new InsertAsyncTask(measurementDao).execute(measurement);
    }

    public void delete(Measurement measurement) {
        new DeleteAsyncTask(measurementDao).execute(measurement);
    }

    public LiveData<List<Measurement>> getAllMeasurements() {
        return mAllMeasurements;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    private static class OperationsAsyncTask extends AsyncTask<Measurement, Void, Void> {
        final MeasurementDao mAsyncTaskDao;

        OperationsAsyncTask(MeasurementDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Measurement... measurements) {
            return null;
        }
    }

    private static class InsertAsyncTask extends OperationsAsyncTask {

        public InsertAsyncTask(MeasurementDao mMeasurementDao) {
            super(mMeasurementDao);
        }

        @Override
        protected Void doInBackground(Measurement... measurements) {
            mAsyncTaskDao.insert(measurements[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends OperationsAsyncTask {

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
