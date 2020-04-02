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

        EditText neck = findViewById(R.id.neck_circumference);
        EditText chest = findViewById(R.id.chest_circumference);
        EditText waist = findViewById(R.id.waist_circumference);
        EditText hips = findViewById(R.id.hips_circumference);
        EditText rightBicep = findViewById(R.id.right_bicep_circumference);
        EditText rightForearm = findViewById(R.id.right_forearm_circumference);
        EditText leftBicep = findViewById(R.id.left_bicep_circumference);
        EditText leftForearm = findViewById(R.id.left_forearm_circumference);
        EditText rightThigh = findViewById(R.id.right_thigh_circumference);
        EditText rightCalf = findViewById(R.id.right_calf_circumference);
        EditText leftThigh = findViewById(R.id.left_thigh_circumference);
        EditText leftCalf = findViewById(R.id.left_calf_circumference);
        EditText weight = findViewById(R.id.weight_measure);
        EditText bodyFat = findViewById(R.id.bodyfat_measure);

        Button saveData = findViewById(R.id.button_save);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent saveMeasurements = new Intent();
                if (TextUtils.isEmpty(neck.getText()) || TextUtils.isEmpty(chest.getText()) ||
                        TextUtils.isEmpty(waist.getText()) || TextUtils.isEmpty(hips.getText()) ||
                        TextUtils.isEmpty(rightBicep.getText()) || TextUtils.isEmpty(rightForearm.getText()) ||
                        TextUtils.isEmpty(leftBicep.getText()) || TextUtils.isEmpty(leftForearm.getText()) ||
                        TextUtils.isEmpty(rightThigh.getText()) || TextUtils.isEmpty(rightCalf.getText()) ||
                        TextUtils.isEmpty(leftThigh.getText()) || TextUtils.isEmpty(leftCalf.getText()) ||
                        TextUtils.isEmpty(weight.getText()) || TextUtils.isEmpty(bodyFat.getText())) {
                    Toast.makeText(getApplicationContext(), "Please enter your measurements or enter 0 to continue", Toast.LENGTH_SHORT).show();
                } else {
                    float neckCirc = Float.parseFloat(neck.getText().toString());
                    float chestCirc = Float.parseFloat(chest.getText().toString());
                    float waistCirc = Float.parseFloat(waist.getText().toString());
                    float hipsCirc = Float.parseFloat(hips.getText().toString());
                    float rightBicepCirc = Float.parseFloat(rightBicep.getText().toString());
                    float rightForearmCirc = Float.parseFloat(rightForearm.getText().toString());
                    float leftBicepCirc = Float.parseFloat(leftBicep.getText().toString());
                    float leftForearmCirc = Float.parseFloat(leftForearm.getText().toString());
                    float rightThighCirc = Float.parseFloat(rightThigh.getText().toString());
                    float rightCalfCirc = Float.parseFloat(rightCalf.getText().toString());
                    float leftThighCirc = Float.parseFloat(leftThigh.getText().toString());
                    float leftCalfCirc = Float.parseFloat(leftCalf.getText().toString());
                    float weightEntry = Float.parseFloat(weight.getText().toString());
                    float bfpercent = Float.parseFloat(bodyFat.getText().toString());

                    saveMeasurements.putExtra("neck", neckCirc);
                    saveMeasurements.putExtra("chest", chestCirc);
                    saveMeasurements.putExtra("waist", waistCirc);
                    saveMeasurements.putExtra("hips", hipsCirc);
                    saveMeasurements.putExtra("rightBicep", rightBicepCirc);
                    saveMeasurements.putExtra("rightForearm", rightForearmCirc);
                    saveMeasurements.putExtra("leftBicep", leftBicepCirc);
                    saveMeasurements.putExtra("leftForearm", leftForearmCirc);
                    saveMeasurements.putExtra("rightThigh", rightThighCirc);
                    saveMeasurements.putExtra("rightCalf", rightCalfCirc);
                    saveMeasurements.putExtra("leftThigh", leftThighCirc);
                    saveMeasurements.putExtra("leftCalf", leftCalfCirc);
                    saveMeasurements.putExtra("weight", weightEntry);
                    saveMeasurements.putExtra("bfpercent", bfpercent);
                    setResult(RESULT_OK, saveMeasurements);
                }

                finish();
            }
        });
    }
}
