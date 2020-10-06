package com.morashstudios.fitnessapp;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.morashstudios.fitnessapp.databinding.FragmentOneRepMaxBinding;

import java.text.DecimalFormat;

public class OneRepMaxFragment extends Fragment {

    private float mWeight;
    private int mReps;
    private float mOneRM;

    private FragmentOneRepMaxBinding binding;


    public OneRepMaxFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOneRepMaxBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.onermResultsButton.setOnClickListener(v -> {
            // Close keyboard
            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            // Execute click
            if (ValidateEntries()) {
                ParseEntries();
                CalculateAndDisplayResults();
            }
        });

        return view;
    }

    private void CalculateAndDisplayResults() {
        DecimalFormat df = new DecimalFormat("0");

        binding.repmaxOnerm.setText(df.format(mOneRM));
        binding.repmaxTworm.setText(df.format(mOneRM * 0.95f));
        binding.repmaxThreerm.setText(df.format(mOneRM * 0.9f));
        binding.repmaxFiverm.setText(df.format(mOneRM * 0.86f));
        binding.repmaxTenrm.setText(df.format(mOneRM * 0.75f));

        binding.onermResultsCard.setVisibility(View.VISIBLE);
    }

    private void ParseEntries() {
        mWeight = Float.parseFloat(String.valueOf(binding.repmaxWeightEntry.getText()));
        mReps = Integer.parseInt(String.valueOf(binding.repmaxRepsEntry.getText()));

        mOneRM = mWeight * (1 + (mReps / 30.0f));
    }

    private boolean ValidateEntries() {
        if (TextUtils.isEmpty(binding.repmaxWeightEntry.getText()) || TextUtils.isEmpty(binding.repmaxRepsEntry.getText()) ||
                Integer.parseInt(String.valueOf(binding.repmaxRepsEntry.getText())) == 0) {
            Toast.makeText(getContext(), "Please enter weight and reps lifted to continue",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
