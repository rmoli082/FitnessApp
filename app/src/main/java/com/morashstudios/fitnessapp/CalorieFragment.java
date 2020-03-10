package com.morashstudios.fitnessapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalorieFragment extends Fragment {

    private int mAge;
    private double mWeight;
    private double mHeight;
    private double mBMR;
    private boolean mIsFemale;
    private double mCalorieModifier;

    public CalorieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_calorie, container, false);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String units = prefs.getString(getString(R.string.settings_unit_key),
                getString(R.string.settings_unit_default));

        Log.i("Units", units);

        RadioGroup genderSelect = view.findViewById(R.id.calorie_gender_select);
        final EditText heightEntry = view.findViewById(R.id.calorie_height_entry);
        final EditText weightEntry = view.findViewById(R.id.calorie_weight_entry);
        final EditText ageEntry = view.findViewById(R.id.calorie_age_entry);
        final Spinner activitySelect = view.findViewById(R.id.calorie_activity_select);
        final TextView calorieResults = view.findViewById(R.id.calorie_results);
        final RelativeLayout resultsTab = view.findViewById(R.id.calorie_results_tab);
        Button calculateCaloriesButton = view.findViewById(R.id.calculate_calories_button);

        if (units.equals(getString(R.string.settings_unit_metric_value))) {
            heightEntry.setHint(R.string.hint_cm);
            weightEntry.setHint(R.string.hint_kg);
        }

        genderSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.calorie_gender_female) {
                    mIsFemale = true;
                } else {
                    mIsFemale = false;
                }
            }
        });

        calculateCaloriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resultsTab.setVisibility(View.VISIBLE);

                mHeight = Double.parseDouble(String.valueOf(heightEntry.getText()));
                mWeight = Double.parseDouble(String.valueOf(weightEntry.getText()));
                mAge = Integer.parseInt(String.valueOf(ageEntry.getText()));

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

                int spinner_pos = activitySelect.getSelectedItemPosition();
                String[] values = getResources().getStringArray(R.array.calories_activity_level_values);
                String activityLevel = values[spinner_pos];

                switch (activityLevel)
                {
                    case "sedentary":
                        mCalorieModifier = 1.1;
                        break;
                    case "light":
                        mCalorieModifier = 1.3;
                        break;
                    case "moderate":
                        mCalorieModifier = 1.5;
                        break;
                    case "intense":
                        mCalorieModifier = 1.6;
                        break;
                    case "very_intense":
                        mCalorieModifier = 1.8;
                        break;
                    default:
                        mCalorieModifier = 1.0;
                }

                mBMR *= mCalorieModifier;
                Log.i("mBMR", String.valueOf(mBMR));

                calorieResults.setText(String.valueOf(Math.round(mBMR)));

            }
        });

        return view;
    }
}
