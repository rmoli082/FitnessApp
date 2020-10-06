package com.morashstudios.fitnessapp;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.morashstudios.fitnessapp.databinding.FragmentBodyfatBinding;

import java.text.DecimalFormat;
import java.util.Objects;

public class BodyfatCalculatorFragment extends Fragment {

    private double mHeight;
    private double mWeight;
    private double mWaist;
    private double mNeck;
    private double mHips;
    private double mBfPercent;
    private double mFatMass;
    private double mLeanMass;
    private boolean mIsFemale;

    private FragmentBodyfatBinding binding;


    public BodyfatCalculatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_bodyfat, container, false);

        binding = FragmentBodyfatBinding.inflate(getLayoutInflater());

        binding.sexButtonGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.sex_button_female) {
                mIsFemale = true;
                binding.bodyfatHipsHeader.setVisibility(View.VISIBLE);
                binding.bodyfatHipsEntry.setVisibility(View.VISIBLE);
                binding.percentageChartFemale.setVisibility(View.VISIBLE);
                binding.percentageChartMale.setVisibility(View.GONE);
                binding.bodyfatWaistHeader.setText(R.string.waist_female);
            } else {
                mIsFemale = false;
                binding.bodyfatHipsHeader.setVisibility(View.GONE);
                binding.bodyfatHipsEntry.setVisibility(View.GONE);
                binding.percentageChartFemale.setVisibility(View.GONE);
                binding.percentageChartMale.setVisibility(View.VISIBLE);
                binding.bodyfatWaistHeader.setText(R.string.waist_male);
            }

        });

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String units = prefs.getString(getString(R.string.settings_unit_key),
                getString(R.string.settings_unit_default));

        if (units.equals(getString(R.string.settings_unit_metric_value))) {

            binding.bodyfatHeightEntry.setHint(R.string.hint_cm);
            binding.bodyfatWeightEntry.setHint(R.string.hint_kg);
            binding.bodyfatWaistEntry.setHint(R.string.hint_cm);
            binding.bodyfatNeckEntry.setHint(R.string.hint_cm);
            binding.bodyfatHipsEntry.setHint(R.string.hint_cm);
            binding.lbsOrKg.setText(R.string.kg);

        }

        binding.calculateBodyfat.setOnClickListener(v -> {

            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            DecimalFormat df = new DecimalFormat("0.0");

            RadioButton sexChoice = view.findViewById(binding.sexButtonGroup.getCheckedRadioButtonId());

            if (sexChoice == null) {
                Toast.makeText(getContext(), "Please select a gender", Toast.LENGTH_SHORT).show();
                return;
            }

            if (String.valueOf(binding.bodyfatHeightEntry.getText()).isEmpty() ||
                    String.valueOf(binding.bodyfatWeightEntry.getText()).isEmpty() ||
                    String.valueOf(binding.bodyfatWaistEntry.getText()).isEmpty() ||
                    String.valueOf(binding.bodyfatNeckEntry.getText()).isEmpty() ||
                    (mIsFemale && String.valueOf(binding.bodyfatHipsEntry.getText()).isEmpty())) {
                Toast.makeText(getContext(), "Please enter your measurements", Toast.LENGTH_SHORT).show();
                return;
            }

            binding.bodyfatResults.setVisibility(View.VISIBLE);

            mHeight = Double.parseDouble(String.valueOf(binding.bodyfatHeightEntry.getText()));
            mWeight = Double.parseDouble(String.valueOf(binding.bodyfatWeightEntry.getText()));
            mWaist = Double.parseDouble(String.valueOf(binding.bodyfatWaistEntry.getText()));
            mNeck = Double.parseDouble(String.valueOf(binding.bodyfatNeckEntry.getText()));

            if (units.equals(getString(R.string.settings_unit_us_value))) {
                mHeight *= 2.54;
                mNeck *= 2.54;
                mWaist *= 2.54;
            }

            if (mIsFemale) {

                mHips = Double.parseDouble(String.valueOf(binding.bodyfatHipsEntry.getText()));
                if (units.equals(getString(R.string.settings_unit_us_value))) {
                    mHips *= 2.54;
                }

                mBfPercent = 495 / (1.29579 - 0.35004 * Math.log10(mWaist + mHips - mNeck) + 0.221 * Math.log10(mHeight)) - 450;
                TextView textView = view.findViewById(R.id.bodyfat_percent_results);
                textView.setText(df.format(mBfPercent));
            } else {
                mBfPercent = 495 / (1.0324 - 0.19077 * Math.log10(mWaist - mNeck) + .15456 * Math.log10(mHeight)) - 450;
                TextView textView = view.findViewById(R.id.bodyfat_percent_results);
                textView.setText(df.format(mBfPercent));
            }

            mFatMass = mWeight * mBfPercent / 100;
            mLeanMass = mWeight - mFatMass;

            binding.bodyfatFatMassResults.setText(df.format(mFatMass));
            binding.bodyfatLeanMassResults.setText(df.format(mLeanMass));

        });

        return view;
    }

}
