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
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
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


    public BodyfatCalculatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_bodyfat, container, false);

        final RadioGroup sexGroup = view.findViewById(R.id.sex_button_group);

        sexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.sex_button_female) {
                    mIsFemale = true;
                    view.findViewById(R.id.bodyfat_hips_header).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.bodyfat_hips_entry).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.percentage_chart_female).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.percentage_chart_male).setVisibility(View.GONE);
                    TextView femaleWaist = view.findViewById(R.id.bodyfat_waist_header);
                    femaleWaist.setText(R.string.waist_female);
                } else {
                    mIsFemale = false;
                    view.findViewById(R.id.bodyfat_hips_header).setVisibility(View.GONE);
                    view.findViewById(R.id.bodyfat_hips_entry).setVisibility(View.GONE);
                    view.findViewById(R.id.percentage_chart_female).setVisibility(View.GONE);
                    view.findViewById(R.id.percentage_chart_male).setVisibility(View.VISIBLE);
                    TextView maleWaist = view.findViewById(R.id.bodyfat_waist_header);
                    maleWaist.setText(R.string.waist_male);
                }

            }
        });

        Button calculateBodyfat = view.findViewById(R.id.calculate_bodyfat);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String units = prefs.getString(getString(R.string.settings_unit_key),
                getString(R.string.settings_unit_default));

        final EditText heightEntry = view.findViewById(R.id.bodyfat_height_entry);
        final EditText weightEntry = view.findViewById(R.id.bodyfat_weight_entry);
        final EditText waistEntry = view.findViewById(R.id.bodyfat_waist_entry);
        final EditText neckEntry = view.findViewById(R.id.bodyfat_neck_entry);
        final EditText hipsEntry = view.findViewById(R.id.bodyfat_hips_entry);
        TextView lbsKgSwitch = view.findViewById(R.id.lbs_or_kg);

        if (units.equals(getString(R.string.settings_unit_metric_value))) {

            heightEntry.setHint(R.string.hint_cm);
            weightEntry.setHint(R.string.hint_kg);
            waistEntry.setHint(R.string.hint_cm);
            neckEntry.setHint(R.string.hint_cm);
            hipsEntry.setHint(R.string.hint_cm);
            lbsKgSwitch.setText(R.string.kg);

        }

        calculateBodyfat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
                    assert imm != null;
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                DecimalFormat df = new DecimalFormat("0.0");

                RadioButton sexChoice = view.findViewById(sexGroup.getCheckedRadioButtonId());

                if (sexChoice == null){
                    Toast.makeText(getContext(), "Please select a gender", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (String.valueOf(heightEntry.getText()).isEmpty() ||
                        String.valueOf(weightEntry.getText()).isEmpty() ||
                        String.valueOf(waistEntry.getText()).isEmpty() ||
                        String.valueOf(neckEntry.getText()).isEmpty() ||
                        (mIsFemale && String.valueOf(hipsEntry.getText()).isEmpty())){
                    Toast.makeText(getContext(), "Please enter your measurements", Toast.LENGTH_SHORT).show();
                    return;
                }

                view.findViewById(R.id.bodyfat_results).setVisibility(View.VISIBLE);

                mHeight = Double.parseDouble(String.valueOf(heightEntry.getText()));
                mWeight = Double.parseDouble(String.valueOf(weightEntry.getText()));
                mWaist = Double.parseDouble(String.valueOf(waistEntry.getText()));
                mNeck = Double.parseDouble(String.valueOf(neckEntry.getText()));

                if (units.equals(getString(R.string.settings_unit_us_value))) {
                    mHeight *= 2.54;
                    mNeck *= 2.54;
                    mWaist *= 2.54;
                }

                if (mIsFemale) {

                    mHips = Double.parseDouble(String.valueOf(hipsEntry.getText()));
                    if (units.equals( getString(R.string.settings_unit_us_value))) {
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

                TextView tv = view.findViewById(R.id.bodyfat_fat_mass_results);
                tv.setText(df.format(mFatMass));
                tv = view.findViewById(R.id.bodyfat_lean_mass_results);
                tv.setText(df.format(mLeanMass));

            }
        });

        return view;
    }

}
