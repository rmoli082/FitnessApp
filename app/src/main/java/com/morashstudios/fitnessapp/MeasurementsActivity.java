package com.morashstudios.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.morashstudios.fitnessapp.Measurements.Measurement;
import com.morashstudios.fitnessapp.Measurements.MeasurementListAdapter;
import com.morashstudios.fitnessapp.Measurements.MeasurementViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MeasurementsActivity extends AppCompatActivity implements MeasurementListAdapter.OnDeleteClickListener {

    private static final int NEW_MEASUREMENT_ACTIVITY_REQ_CODE = 1;
    private MeasurementViewModel measurementViewModel;
    private MeasurementListAdapter measurementListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurements);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        measurementListAdapter = new MeasurementListAdapter(this, this);
        recyclerView.setAdapter(measurementListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton addFab = findViewById(R.id.add_measurement_fab);
        addFab.setOnClickListener(v -> {
            Intent intent = new Intent(MeasurementsActivity.this, AddMeasurementActivity.class);
            startActivityForResult(intent, NEW_MEASUREMENT_ACTIVITY_REQ_CODE);

        });

        measurementViewModel = new ViewModelProvider(this).get(MeasurementViewModel.class);

        measurementViewModel.getAllMeasurements().observe(this, measurements -> measurementListAdapter.setMeasurements(measurements));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_MEASUREMENT_ACTIVITY_REQ_CODE && resultCode == RESULT_OK) {

            SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd yyyy, HH:mm:ss", Locale.getDefault());

            Measurement measurement = new Measurement(df.format(new Date()),
                    data.getFloatExtra("neck", 0f),
                    data.getFloatExtra("chest", 0f),
                    data.getFloatExtra("waist", 0f),
                    data.getFloatExtra("hips", 0f),
                    data.getFloatExtra("rBicep", 0f),
                    data.getFloatExtra("rForearm", 0f),
                    data.getFloatExtra("lBicep", 0f),
                    data.getFloatExtra("lForearm", 0f),
                    data.getFloatExtra("rThigh", 0f),
                    data.getFloatExtra("rCalf", 0f),
                    data.getFloatExtra("lThigh", 0f),
                    data.getFloatExtra("lCalf", 0f),
                    data.getFloatExtra("weight", 0f),
                    data.getFloatExtra("bodyfat", 0f));
            measurementViewModel.insert(measurement);

            Toast.makeText(getApplicationContext(), R.string.saved, Toast.LENGTH_LONG).show();

        } else {

            Toast.makeText(getApplicationContext(), R.string.not_saved, Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void OnDeleteCLickListener(Measurement myMeasurement) {
        //Code for delete operation
        measurementViewModel.delete(myMeasurement);
    }
}
