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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.morashstudios.fitnessapp.databinding.FragmentBodyindexBinding;

import java.text.DecimalFormat;


public class BodyIndexFragment extends Fragment {

    private double mHeight;
    private double mWeight;
    private double mBodyIndex;
    private String units;

    public BodyIndexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_bodyindex, container, false);

        com.morashstudios.fitnessapp.databinding.FragmentBodyindexBinding binding = FragmentBodyindexBinding.inflate(getLayoutInflater());

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        units = sharedPrefs.getString(getString(R.string.settings_unit_key),
                getString(R.string.settings_unit_default));

        assert units != null;
        if (units.equals(getString(R.string.settings_unit_metric_value))) {
            binding.bmiWeightEntry.setHint(getString(R.string.hint_kg));
            binding.bmiHeightEntry.setHint(R.string.hint_cm);
        }

        Button resultButton = view.findViewById(R.id.bmi_results_button);

        resultButton.setOnClickListener(v -> {

            EditText weightEntry = view.findViewById(R.id.bmi_weight_entry);
            EditText heightEntry = view.findViewById(R.id.bmi_height_entry);
            TextView bmiResults = view.findViewById(R.id.bmi_result);
            DecimalFormat df = new DecimalFormat("0.0");

            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            if (TextUtils.isEmpty(weightEntry.getText()) ||
                    TextUtils.isEmpty(heightEntry.getText())) {
                Toast.makeText(getContext(), "Please enter your measurements", Toast.LENGTH_SHORT).show();
                return;
            }

            CardView resultsTab = view.findViewById(R.id.bmi_results_tab);
            resultsTab.setVisibility(View.VISIBLE);

            mWeight = Double.parseDouble(String.valueOf(weightEntry.getText()));
            mHeight = Double.parseDouble(String.valueOf(heightEntry.getText()));

            CalculateBMI();

            bmiResults.setText(df.format(CalculateBMI()));
        });

        return view;
    }

    private double CalculateBMI() {
        if (units.equals(getString(R.string.settings_unit_us_value))) {
            mWeight /= 2.2;
            mHeight *= 2.54;
        }

        mHeight /= 100;
        mBodyIndex = mWeight / Math.pow(mHeight, 2);

        return mBodyIndex;
    }
}
