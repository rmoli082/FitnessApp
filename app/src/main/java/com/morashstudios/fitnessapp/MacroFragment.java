package com.morashstudios.fitnessapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DecimalFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class MacroFragment extends Fragment {

    private int mCalorieNeeds;
    private double mFatPercent;
    private double mProteinPercent;
    private double mCarbsPercent;

    public MacroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_macro, container, false);

        RadioGroup dietGoalSelect = view.findViewById(R.id.macro_diet_select);

        dietGoalSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.macro_diet_default_button:
                        mCarbsPercent = 0.5;
                        mProteinPercent = 0.3;
                        mFatPercent = 0.2;
                        break;
                    case R.id.macro_diet_keto_button:
                        mCarbsPercent = 0.15;
                        mProteinPercent = 0.15;
                        mFatPercent = 0.7;
                        break;
                }
            }
        });

        final EditText calorieEntry = view.findViewById(R.id.macro_calorie_entry);

        Button getResultsButton = view.findViewById(R.id.macro_get_results_button);
        getResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalorieNeeds = Integer.parseInt(String.valueOf(calorieEntry.getText()));
                DecimalFormat df = new DecimalFormat("0.0");
                TextView carbAmount = view.findViewById(R.id.macro_carb_result);
                TextView proteinAmount = view.findViewById(R.id.macro_protein_result);
                TextView fatAmount = view.findViewById(R.id.macro_fat_result);

                carbAmount.setText(df.format((mCalorieNeeds*mCarbsPercent)/4));
                proteinAmount.setText(df.format((mCalorieNeeds*mProteinPercent)/4));
                fatAmount.setText(df.format((mCalorieNeeds*mFatPercent)/9));
            }
        });

        return view;
    }
}
