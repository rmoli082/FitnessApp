package com.moshra.fitnessapp;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

    private int mHeight;
    private int mWeight;
    private int mWaist;
    private int mNeck;
    private int mHips;
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

        calculateBodyfat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                view.findViewById(R.id.bodyfat_results).setVisibility(View.VISIBLE);

                EditText entryText = view.findViewById(R.id.bodyfat_height_entry);
                mHeight = Integer.parseInt(String.valueOf(entryText.getText()));
                entryText = view.findViewById(R.id.bodyfat_weight_entry);
                mWeight = Integer.parseInt(String.valueOf(entryText.getText()));
                entryText = view.findViewById(R.id.bodyfat_waist_entry);
                mWaist = Integer.parseInt(String.valueOf(entryText.getText()));
                entryText = view.findViewById(R.id.bodyfat_neck_entry);
                mNeck = Integer.parseInt(String.valueOf(entryText.getText()));

                if (mIsFemale) {
                    entryText = view.findViewById(R.id.bodyfat_hips_entry);
                    mHips = Integer.parseInt(String.valueOf(entryText.getText()));
                    mBfPercent = 495 / (1.29579 - (0.35004 * Math.log10(mWaist + mHips - mNeck)) + (0.221 * Math.log10(mHeight))) - 450;
                    TextView textView = view.findViewById(R.id.bodyfat_percent_results);
                    textView.setText(String.valueOf(mBfPercent));
                } else {
                    mBfPercent = 495 / (1.0324 - (0.19077 * Math.log10(mWaist - mNeck)) + .15456 * Math.log10(mHeight)) - 450;
                    TextView textView = view.findViewById(R.id.bodyfat_percent_results);
                    textView.setText(String.valueOf(mBfPercent));
                }

            }
        });

        return view;
    }

}
