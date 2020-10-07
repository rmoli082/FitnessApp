package com.morashstudios.fitnessapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.morashstudios.fitnessapp.databinding.FragmentCalorieBinding;

import java.util.Objects;

public class CalorieFragment extends Fragment {

    private int mAge;
    private double mWeight;
    private double mHeight;
    private double mBMR;
    private boolean mIsFemale;

    private String units;

    private FragmentCalorieBinding binding;

    public CalorieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = FragmentCalorieBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        SetUnitPrefsAndGender();


        binding.calculateCaloriesButton.setOnClickListener(v -> {
            // Close keyboard
            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);


            if (ValidateEntries()) {
                ParseEntries();
                CalculateCalories();
                DisplayResults();
            }
        });

        return view;
    }

    private void DisplayResults() {
        binding.calorieResults.setText(String.valueOf(Math.round(mBMR)));
        binding.calorieResultsTab.setVisibility(View.VISIBLE);
    }

    private void CalculateCalories() {
        if (units.equals(getString(R.string.settings_unit_us_value))) {
            mHeight *= 2.54;
            mWeight /= 2.2;
        }

        mBMR = (10 * mWeight) + (6.25 * mHeight) - (5 * mAge);

        if (mIsFemale) {
            mBMR -= 161;
        } else {
            mBMR += 5;
        }

        // Apply activity modifier from spinner selection
        mBMR *= GetCalorieModifierFromSpinner();
    }

    private double GetCalorieModifierFromSpinner() {
        double calorieModifier;
        int spinner_pos = binding.calorieActivitySelect.getSelectedItemPosition();
        String[] values = getResources().getStringArray(R.array.calories_activity_level_values);
        String activityLevel = values[spinner_pos];

        switch (activityLevel) {
            case "sedentary":
                calorieModifier = 1;
                break;
            case "light":
                calorieModifier = 1.15;
                break;
            case "moderate":
                calorieModifier = 1.25;
                break;
            case "heavy":
                calorieModifier = 1.5;
                break;
            case "intense":
                calorieModifier = 1.4;
                break;
            case "very_intense":
                calorieModifier = 1.75;
                break;
            default:
                calorieModifier = 1.0;
        }

        return calorieModifier;
    }

    private void ParseEntries() {
        mHeight = Double.parseDouble(String.valueOf(binding.calorieHeightEntry.getText()));
        mWeight = Double.parseDouble(String.valueOf(binding.calorieWeightEntry.getText()));
        mAge = Integer.parseInt(String.valueOf(binding.calorieAgeEntry.getText()));
    }

    private boolean ValidateEntries() {

        // Check required entries have input
        if (TextUtils.isEmpty(binding.calorieHeightEntry.getText()) ||
                TextUtils.isEmpty(binding.calorieWeightEntry.getText()) ||
                TextUtils.isEmpty(binding.calorieAgeEntry.getText())) {
            Toast.makeText(getContext(), "Please enter your measurements", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void SetUnitPrefsAndGender() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        units = prefs.getString(getString(R.string.settings_unit_key),
                getString(R.string.settings_unit_default));
        mIsFemale = Objects.equals(prefs.getString(getString(R.string.settings_gender_key), getString(R.string.male)), getString(R.string.female));

        assert units != null;
        if (units.equals(getString(R.string.settings_unit_metric_value))) {
            binding.calorieHeightEntry.setHint(R.string.hint_cm);
            binding.calorieWeightEntry.setHint(R.string.hint_kg);
        }
    }

}
