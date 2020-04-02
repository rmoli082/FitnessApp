package com.morashstudios.fitnessapp.database;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MeasurementsViewModel extends AndroidViewModel {

    private MeasurementsRepository mRepository;
    private LiveData<List<Measurements>> mAllMeasurements;
    public MeasurementsViewModel(Application application) {
        super(application);
        mRepository = new MeasurementsRepository(application);
        mAllMeasurements = mRepository.getAllMeasurements();
    }

    public LiveData<List<Measurements>> getAllMeasurements() {return mAllMeasurements;}

    public void insert (Measurements measurements) {mRepository.insert(measurements);}
}