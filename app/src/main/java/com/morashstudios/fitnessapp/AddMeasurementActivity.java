package com.morashstudios.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddMeasurementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_measurement);

        Button saveMeasurement = findViewById(R.id.save_measurement_button);
        saveMeasurement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();

                //TODO: add the measurement data and return intent code
                //if (measurements are empty) {
                //  setResult(RESULT_CANCELLED, resultIntent);
                //} else {
                //      resultIntent.putExtra(add the data here);
                //      setResult(RESULT_OK, resultIntent);
                //}

                finish();

            }
        });
    }
}
