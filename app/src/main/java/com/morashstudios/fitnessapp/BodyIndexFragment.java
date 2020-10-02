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
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.morashstudios.fitnessapp.databinding.FragmentBodyindexBinding;

import java.text.DecimalFormat;
import java.util.Objects;


public class BodyIndexFragment extends Fragment {

    private double mHeight;
    private double mWeight;
    private double mBodyIndex;

    private FragmentBodyindexBinding binding;

    public BodyIndexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_bodyindex, container, false);

        binding = FragmentBodyindexBinding.inflate(getLayoutInflater());

        final DecimalFormat df = new DecimalFormat("0.0");

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String units = sharedPrefs.getString(getString(R.string.settings_unit_key),
                getString(R.string.settings_unit_default));

        if (units.equals(getString(R.string.settings_unit_metric_value))) {
            binding.bmiWeightEntry.setHint(getString(R.string.hint_kg));
            binding.bmiHeightEntry.setHint(R.string.hint_cm);
        }

        Button getResults = view.findViewById(R.id.bmi_results_button);

        getResults.setOnClickListener(v -> {

            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            if (String.valueOf(binding.bmiWeightEntry.getText()).isEmpty() ||
                    String.valueOf(binding.bmiHeightEntry.getText()).isEmpty()) {
                Toast.makeText(getContext(), "Please enter your measurements", Toast.LENGTH_SHORT).show();
                return;
            }

            view.findViewById(R.id.bmi_results_tab).setVisibility(View.VISIBLE);

            mWeight = Double.parseDouble(String.valueOf(binding.bmiWeightEntry.getText()));
            mHeight = Double.parseDouble(String.valueOf(binding.bmiHeightEntry.getText()));

            if (units.equals(getString(R.string.settings_unit_us_value))) {
                mWeight /= 2.2;
                mHeight *= 2.54;
            }

            mHeight /= 100;
            mBodyIndex = mWeight / Math.pow(mHeight, 2);

            binding.bmiResult.setText(df.format(mBodyIndex));
        });

        return view;
    }
}
