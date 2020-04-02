package com.morashstudios.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMeasurementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_measurement);

        EditText neck = findViewById(R.id.add_neck);
        EditText chest = findViewById(R.id.add_chest);
        EditText waist = findViewById(R.id.add_waist);
        EditText hips = findViewById(R.id.add_hips);
        EditText rBicep = findViewById(R.id.add_rBicep);
        EditText lBicep = findViewById(R.id.add_lBicep);
        EditText rForearm = findViewById(R.id.add_rForearm);
        EditText lForearm = findViewById(R.id.add_lForearm);
        EditText rThigh = findViewById(R.id.add_rThigh);
        EditText lThigh = findViewById(R.id.add_lThigh);
        EditText rCalf = findViewById(R.id.add_rCalf);
        EditText lCalf = findViewById(R.id.add_lCalf);
        EditText weight = findViewById(R.id.add_weight);
        EditText bodyfat = findViewById(R.id.add_bodyfat);

        Button saveMeasurement = findViewById(R.id.save_measurement_button);
        saveMeasurement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();

                if (TextUtils.isEmpty(neck.getText()) || TextUtils.isEmpty(chest.getText()) ||
                        TextUtils.isEmpty(waist.getText()) || TextUtils.isEmpty(hips.getText()) ||
                        TextUtils.isEmpty(rBicep.getText()) || TextUtils.isEmpty(lBicep.getText()) ||
                        TextUtils.isEmpty(rForearm.getText()) || TextUtils.isEmpty(lForearm.getText()) ||
                        TextUtils.isEmpty(rThigh.getText()) || TextUtils.isEmpty(lThigh.getText()) ||
                        TextUtils.isEmpty(rCalf.getText()) || TextUtils.isEmpty(lCalf.getText()) ||
                        TextUtils.isEmpty(bodyfat.getText()) || TextUtils.isEmpty(bodyfat.getText())) {
                    Toast.makeText(AddMeasurementActivity.this, R.string.enterstuffpls, Toast.LENGTH_LONG).show();
                } else {
                    resultIntent.putExtra("neck", Float.parseFloat(String.valueOf(neck.getText())));
                    resultIntent.putExtra("chest", Float.parseFloat(String.valueOf(chest.getText())));
                    resultIntent.putExtra("waist", Float.parseFloat(String.valueOf(waist.getText())));
                    resultIntent.putExtra("hips", Float.parseFloat(String.valueOf(hips.getText())));
                    resultIntent.putExtra("rBicep", Float.parseFloat(String.valueOf(rBicep.getText())));
                    resultIntent.putExtra("lBicep", Float.parseFloat(String.valueOf(lBicep.getText())));
                    resultIntent.putExtra("rForearm", Float.parseFloat(String.valueOf(rForearm.getText())));
                    resultIntent.putExtra("lForearm", Float.parseFloat(String.valueOf(lForearm.getText())));
                    resultIntent.putExtra("rThigh", Float.parseFloat(String.valueOf(rThigh.getText())));
                    resultIntent.putExtra("lThigh", Float.parseFloat(String.valueOf(lThigh.getText())));
                    resultIntent.putExtra("rCalf", Float.parseFloat(String.valueOf(rCalf.getText())));
                    resultIntent.putExtra("lCalf", Float.parseFloat(String.valueOf(lCalf.getText())));
                    resultIntent.putExtra("weight", Float.parseFloat(String.valueOf(weight.getText())));
                    resultIntent.putExtra("bodyfat", Float.parseFloat(String.valueOf(bodyfat.getText())));
                    setResult(RESULT_OK, resultIntent);
                }

                finish();

            }
        });
    }
}
