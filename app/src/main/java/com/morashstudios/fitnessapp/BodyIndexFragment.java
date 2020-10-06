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

import androidx.fragment.app.Fragment;

import com.morashstudios.fitnessapp.databinding.FragmentBodyindexBinding;

import java.text.DecimalFormat;


public class BodyIndexFragment extends Fragment {

    private double mHeight;
    private double mWeight;
    private double mBodyIndex;
    private String units;
    private FragmentBodyindexBinding binding;

    public BodyIndexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = FragmentBodyindexBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Load SharedPreferences to get units
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        units = sharedPrefs.getString(getString(R.string.settings_unit_key),
                getString(R.string.settings_unit_default));

        // Set units in hints
        assert units != null;
        if (units.equals(getString(R.string.settings_unit_metric_value))) {
            binding.bmiWeightEntry.setHint(getString(R.string.hint_kg));
            binding.bmiHeightEntry.setHint(R.string.hint_cm);
        }

        binding.bmiResultsButton.setOnClickListener(v -> {

            DecimalFormat df = new DecimalFormat("0.0");

            // Close software keyboard
            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            // Check to ensure entries completed
            if (TextUtils.isEmpty(binding.bmiWeightEntry.getText()) ||
                    TextUtils.isEmpty(binding.bmiHeightEntry.getText())) {
                Toast.makeText(getContext(), "Please enter your measurements", Toast.LENGTH_SHORT).show();
                return;
            }

            ParseEntries();

            binding.bmiResultsTab.setVisibility(View.VISIBLE);
            binding.bmiResult.setText(df.format(CalculateBMI()));
        });

        return view;
    }

    private void ParseEntries() {
        mWeight = Double.parseDouble(String.valueOf(binding.bmiWeightEntry.getText()));
        mHeight = Double.parseDouble(String.valueOf(binding.bmiHeightEntry.getText()));
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
