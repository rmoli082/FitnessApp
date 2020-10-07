package com.morashstudios.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.morashstudios.fitnessapp.databinding.ActivityAddMeasurementBinding;

public class AddMeasurementActivity extends AppCompatActivity {

    private ActivityAddMeasurementBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMeasurementBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Button saveMeasurementButton = view.findViewById(R.id.save_measurement_button);
        saveMeasurementButton.setOnClickListener(v -> {
            Intent resultIntent = new Intent();

            Log.e("Click", "clicked");

            if (TextUtils.isEmpty(binding.addNeck.getText()) || TextUtils.isEmpty(binding.addChest.getText()) ||
                    TextUtils.isEmpty(binding.addWaist.getText()) || TextUtils.isEmpty(binding.addHips.getText()) ||
                    TextUtils.isEmpty(binding.addRBicep.getText()) || TextUtils.isEmpty(binding.addLBicep.getText()) ||
                    TextUtils.isEmpty(binding.addRForearm.getText()) || TextUtils.isEmpty(binding.addLForearm.getText()) ||
                    TextUtils.isEmpty(binding.addRThigh.getText()) || TextUtils.isEmpty(binding.addLThigh.getText()) ||
                    TextUtils.isEmpty(binding.addRCalf.getText()) || TextUtils.isEmpty(binding.addLCalf.getText()) ||
                    TextUtils.isEmpty(binding.addWeight.getText()) || TextUtils.isEmpty(binding.addBodyfat.getText())) {
                Toast.makeText(AddMeasurementActivity.this, R.string.enter_stuff, Toast.LENGTH_LONG).show();
                return;
            }

            resultIntent.putExtra("neck", Float.parseFloat(String.valueOf(binding.addNeck.getText())));
            resultIntent.putExtra("chest", Float.parseFloat(String.valueOf(binding.addChest.getText())));
            resultIntent.putExtra("waist", Float.parseFloat(String.valueOf(binding.addWaist.getText())));
            resultIntent.putExtra("hips", Float.parseFloat(String.valueOf(binding.addHips.getText())));
            resultIntent.putExtra("rBicep", Float.parseFloat(String.valueOf(binding.addRBicep.getText())));
            resultIntent.putExtra("lBicep", Float.parseFloat(String.valueOf(binding.addLBicep.getText())));
            resultIntent.putExtra("rForearm", Float.parseFloat(String.valueOf(binding.addRForearm.getText())));
            resultIntent.putExtra("lForearm", Float.parseFloat(String.valueOf(binding.addLForearm.getText())));
            resultIntent.putExtra("rThigh", Float.parseFloat(String.valueOf(binding.addRThigh.getText())));
            resultIntent.putExtra("lThigh", Float.parseFloat(String.valueOf(binding.addLThigh.getText())));
            resultIntent.putExtra("rCalf", Float.parseFloat(String.valueOf(binding.addRCalf.getText())));
            resultIntent.putExtra("lCalf", Float.parseFloat(String.valueOf(binding.addLCalf.getText())));
            resultIntent.putExtra("weight", Float.parseFloat(String.valueOf(binding.addWeight.getText())));
            resultIntent.putExtra("bodyfat", Float.parseFloat(String.valueOf(binding.addBodyfat.getText())));
            setResult(RESULT_OK, resultIntent);

            Log.e("Get Results", String.valueOf(resultIntent.getFloatExtra("neck", 0)));
            finish();
        });
    }
}
