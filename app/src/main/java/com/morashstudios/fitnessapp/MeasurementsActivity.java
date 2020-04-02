package com.morashstudios.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.morashstudios.fitnessapp.database.Measurements;
import com.morashstudios.fitnessapp.database.MeasurementsAdapter;
import com.morashstudios.fitnessapp.database.MeasurementsViewModel;

import java.util.Date;
import java.util.List;

public class MeasurementsActivity extends AppCompatActivity {

    private MeasurementsViewModel mMeasurementsViewModel;
    public static final int ACTIVITY_REQUEST_CODE = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_measurements);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final MeasurementsAdapter adapter = new MeasurementsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMeasurementsViewModel = new ViewModelProvider(this).get(MeasurementsViewModel.class);

        mMeasurementsViewModel.getAllMeasurements().observe(this, new Observer<List<Measurements>>() {
            @Override
            public void onChanged(List<Measurements> measurements) {
                adapter.setMeasurements(measurements);
            }
        });

        FloatingActionButton addMeasurements = findViewById(R.id.fab);

        addMeasurements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addNew = new Intent(MeasurementsActivity.this, AddMeasurementActivity.class);
                startActivityForResult(addNew, ACTIVITY_REQUEST_CODE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Measurements measurement = new Measurements(new Date().toString(),
                    data.getFloatExtra("neck", 0),
                    data.getFloatExtra("chest", 0),
                    data.getFloatExtra("waist", 0),
                    data.getFloatExtra("hips", 0),
                    data.getFloatExtra("rightBicep", 0),
                    data.getFloatExtra("rightForearm", 0),
                    data.getFloatExtra("leftBicep", 0),
                    data.getFloatExtra("leftForearm", 0),
                    data.getFloatExtra("rightThigh", 0),
                    data.getFloatExtra("rightCalf", 0),
                    data.getFloatExtra("leftThigh", 0),
                    data.getFloatExtra("leftCalf", 0),
                    data.getFloatExtra("waist", 0),
                    data.getFloatExtra("bfpercent", 0));

            mMeasurementsViewModel.insert(measurement);
        } else {
            Toast.makeText(MeasurementsActivity.this, "Measurements not saved", Toast.LENGTH_LONG).show();
        }
    }
}
