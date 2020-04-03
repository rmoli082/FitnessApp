package com.morashstudios.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MeasurementsActivity extends AppCompatActivity {

    private static final int NEW_MEASUREMENT_ACTIVITY_REQ_CODE = 1;
    private MeasurementViewModel measurementViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurements);

        FloatingActionButton addFab = findViewById(R.id.add_measurement_fab);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MeasurementsActivity.this, AddMeasurementActivity.class);
                startActivityForResult(intent, NEW_MEASUREMENT_ACTIVITY_REQ_CODE);

            }
        });

        measurementViewModel = ViewModelProviders.of(this).get(MeasurementViewModel.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_MEASUREMENT_ACTIVITY_REQ_CODE && resultCode == RESULT_OK) {

            // Code to insert note
            Measurement measurement = new Measurement (data.getFloatExtra("neck", 0));
            measurementViewModel.insert(measurement);

            Toast.makeText(getApplicationContext(), R.string.saved, Toast.LENGTH_LONG).show();

        } else {

            Toast.makeText(getApplicationContext(), R.string.not_saved, Toast.LENGTH_LONG).show();

        }
    }
}
