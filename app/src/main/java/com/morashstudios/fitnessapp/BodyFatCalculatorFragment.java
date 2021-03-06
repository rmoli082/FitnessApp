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

import com.morashstudios.fitnessapp.databinding.FragmentBodyfatBinding;

import java.text.DecimalFormat;
import java.util.Objects;

public class BodyFatCalculatorFragment extends Fragment {

    private double mHeight;
    private double mWeight;
    private double mWaist;
    private double mNeck;
    private double mHips;
    private double mBfPercent;
    private double mFatMass;
    private double mLeanMass;
    private boolean mIsFemale;

    private String units;

    private FragmentBodyfatBinding binding;


    public BodyFatCalculatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate view and bindings
        binding = FragmentBodyfatBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        SetUnitPrefsAndGender();

        binding.calculateBodyfat.setOnClickListener(v -> {
            // Close keyboard
            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            // Execute click
            if (ValidateEntries()) {
                ParseEntries();
                CalculateBodyFat();
                DisplayResults();
            }
        });

        return view;
    }

    private void SetUnitPrefsAndGender() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        units = prefs.getString(getString(R.string.settings_unit_key),
                getString(R.string.settings_unit_default));
        mIsFemale = Objects.equals(prefs.getString(getString(R.string.settings_gender_key), getString(R.string.male)), getString(R.string.female));

        assert units != null;
        if (units.equals(getString(R.string.settings_unit_metric_value))) {

            binding.bodyfatHeightEntry.setHint(R.string.hint_cm);
            binding.bodyfatWeightEntry.setHint(R.string.hint_kg);
            binding.bodyfatWaistEntry.setHint(R.string.hint_cm);
            binding.bodyfatNeckEntry.setHint(R.string.hint_cm);
            binding.bodyfatHipsEntry.setHint(R.string.hint_cm);
            binding.lbsOrKg.setText(R.string.kg);
        }

        if (mIsFemale) {
            binding.bodyfatHipsHeader.setVisibility(View.VISIBLE);
            binding.bodyfatHipsEntry.setVisibility(View.VISIBLE);
        } else {
            binding.bodyfatHipsHeader.setVisibility(View.GONE);
            binding.bodyfatHipsEntry.setVisibility(View.GONE);
        }
    }

    private boolean ValidateEntries() {
        if (TextUtils.isEmpty(binding.bodyfatHeightEntry.getText()) ||
                TextUtils.isEmpty(binding.bodyfatWeightEntry.getText()) ||
                TextUtils.isEmpty(binding.bodyfatWaistEntry.getText()) ||
                TextUtils.isEmpty(binding.bodyfatNeckEntry.getText()) ||
                (mIsFemale && TextUtils.isEmpty(binding.bodyfatHipsEntry.getText()))) {
            Toast.makeText(getContext(), "Please enter all measurements", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void ParseEntries() {
        mHeight = Double.parseDouble(String.valueOf(binding.bodyfatHeightEntry.getText()));
        mWeight = Double.parseDouble(String.valueOf(binding.bodyfatWeightEntry.getText()));
        mWaist = Double.parseDouble(String.valueOf(binding.bodyfatWaistEntry.getText()));
        mNeck = Double.parseDouble(String.valueOf(binding.bodyfatNeckEntry.getText()));
        if (mIsFemale)
            mHips = Double.parseDouble(String.valueOf(binding.bodyfatHipsEntry.getText()));
    }

    private void CalculateBodyFat() {
        if (units.equals(getString(R.string.settings_unit_us_value))) {
            mHeight *= 2.54;
            mNeck *= 2.54;
            mWaist *= 2.54;
        }

        if (mIsFemale) {
            if (units.equals(getString(R.string.settings_unit_us_value))) {
                mHips *= 2.54;
            }
            mBfPercent = 495 / (1.29579 - 0.35004 * Math.log10(mWaist + mHips - mNeck) + 0.221 * Math.log10(mHeight)) - 450;
        } else {
            mBfPercent = 495 / (1.0324 - 0.19077 * Math.log10(mWaist - mNeck) + .15456 * Math.log10(mHeight)) - 450;
        }

        mFatMass = mWeight * mBfPercent / 100;
        mLeanMass = mWeight - mFatMass;
    }

    private void DisplayResults() {
        DecimalFormat df = new DecimalFormat("0.0");

        binding.bodyfatPercentResults.setText(df.format(mBfPercent));
        binding.bodyfatFatMassResults.setText(df.format(mFatMass));
        binding.bodyfatLeanMassResults.setText(df.format(mLeanMass));

        binding.bodyfatResults.setVisibility(View.VISIBLE);
    }

}
