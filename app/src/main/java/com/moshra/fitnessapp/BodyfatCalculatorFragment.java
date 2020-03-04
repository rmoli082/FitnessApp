package com.moshra.fitnessapp;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    private boolean mIsFemale;


    public BodyfatCalculatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_bodyfat, container, false);

        final RadioGroup sexGroup = view.findViewById(R.id.sex_button_group);
        RadioButton sexChoice = view.findViewById(sexGroup.getCheckedRadioButtonId());

        sexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (sexGroup.getCheckedRadioButtonId() == R.id.sex_button_female) {
                    mIsFemale = true;
                    view.findViewById(R.id.bodyfat_female_options).setVisibility(View.VISIBLE);
                    TextView femaleWaist = view.findViewById(R.id.bodyfat_waist_header);
                    femaleWaist.setText(R.string.waist_female);
                } else {
                    mIsFemale = false;
                    view.findViewById(R.id.bodyfat_female_options).setVisibility(View.GONE);
                    TextView maleWaist = view.findViewById(R.id.bodyfat_waist_header);
                    maleWaist.setText(R.string.waist_male);
                }

            }
        });

        Button calculateBodyfat = view.findViewById(R.id.calculate_bodyfat);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        final String units = prefs.getString(getString(R.string.settings_unit_key),
                getString(R.string.settings_unit_default));

        final EditText heightEntry = view.findViewById(R.id.bodyfat_height_entry);
        final EditText weightEntry = view.findViewById(R.id.bodyfat_weight_entry);
        final EditText waistEntry = view.findViewById(R.id.bodyfat_waist_entry);
        final EditText neckEntry = view.findViewById(R.id.bodyfat_neck_entry);
        final EditText hipsEntry = view.findViewById(R.id.bodyfat_hips_entry);

        if (units.equals(getString(R.string.settings_unit_metric_value))) {

            heightEntry.setHint(R.string.hint_cm);
            weightEntry.setHint(R.string.hint_kg);
            waistEntry.setHint(getString(R.string.hint_cm));
            neckEntry.setHint(R.string.hint_cm);
            hipsEntry.setHint(R.string.hint_cm);

        }

        calculateBodyfat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                view.findViewById(R.id.bodyfat_results).setVisibility(View.VISIBLE);

                mHeight = Integer.parseInt(String.valueOf(heightEntry.getText()));
                mWeight = Integer.parseInt(String.valueOf(weightEntry.getText()));
                mWaist = Integer.parseInt(String.valueOf(waistEntry.getText()));
                mNeck = Integer.parseInt(String.valueOf(neckEntry.getText()));

                if (units == getString(R.string.settings_unit_us_value)) {
                    mHeight *= 2.54;
                    mNeck *= 2.54;
                    mWaist *= 2.54;
                    mWeight /= 2.2;
                }

                if (mIsFemale) {
                    mHips = Integer.parseInt(String.valueOf(hipsEntry.getText()));
                    if (units == getString(R.string.settings_unit_us_value)) {
                        mHips *= 2.54;
                    }
                    mBfPercent = 495 / (1.29579 - 0.35004 * Math.log10(mWaist + mHips - mNeck) + 0.221 * Math.log10(mHeight)) - 450;
                    TextView textView = view.findViewById(R.id.bodyfat_percent_results);
                    textView.setText(String.valueOf(mBfPercent));
                } else {
                    mBfPercent = 495 / (1.0324 - 0.19077 * Math.log10(mWaist - mNeck) + .15456 * Math.log10(mHeight)) - 450;
                    TextView textView = view.findViewById(R.id.bodyfat_percent_results);
                    textView.setText(String.valueOf(mBfPercent));
                }

            }
        });

        return view;
    }

}
