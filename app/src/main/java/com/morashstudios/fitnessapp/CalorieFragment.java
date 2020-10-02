package com.morashstudios.fitnessapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.morashstudios.fitnessapp.databinding.FragmentCalorieBinding;

import java.util.Objects;


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

    private FragmentCalorieBinding binding;

    public CalorieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_calorie, container, false);

        binding = FragmentCalorieBinding.inflate(getLayoutInflater());

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String units = prefs.getString(getString(R.string.settings_unit_key),
                getString(R.string.settings_unit_default));

        if (units.equals(getString(R.string.settings_unit_metric_value))) {
            binding.calorieHeightEntry.setHint(R.string.hint_cm);
            binding.calorieWeightEntry.setHint(R.string.hint_kg);
        }

        binding.calorieGenderSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mIsFemale = checkedId == R.id.calorie_gender_female;
            }
        });

        binding.calculateCaloriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
                assert imm != null;
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                RadioButton genderChoice = view.findViewById(binding.calorieGenderSelect.getCheckedRadioButtonId());

                if (genderChoice == null) {
                    Toast.makeText(getContext(), "Please select a gender", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (String.valueOf(binding.calorieHeightEntry.getText()).isEmpty() ||
                        String.valueOf(binding.calorieWeightEntry.getText()).isEmpty() ||
                        String.valueOf(binding.calorieAgeEntry.getText()).isEmpty()) {
                    Toast.makeText(getContext(), "Please enter your measurements", Toast.LENGTH_SHORT).show();
                    return;
                }

                view.findViewById(R.id.calorie_results_tab).setVisibility(View.VISIBLE);

                mHeight = Double.parseDouble(String.valueOf(binding.calorieHeightEntry.getText()));
                mWeight = Double.parseDouble(String.valueOf(binding.calorieWeightEntry.getText()));
                mAge = Integer.parseInt(String.valueOf(binding.calorieAgeEntry.getText()));

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

                int spinner_pos = binding.calorieActivitySelect.getSelectedItemPosition();
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
                        mCalorieModifier = 1.4;
                        break;
                    case "heavy":
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

                binding.calorieResults.setText(String.valueOf(Math.round(mBMR)));

            }
        });

        return view;
    }
}
