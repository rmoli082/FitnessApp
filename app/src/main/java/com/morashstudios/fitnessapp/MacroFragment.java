package com.morashstudios.fitnessapp;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.morashstudios.fitnessapp.databinding.FragmentMacroBinding;


public class MacroFragment extends Fragment {

    private int mCalorieNeeds;
    private double mFatPercent;
    private double mProteinPercent;
    private double mCarbsPercent;

    private FragmentMacroBinding binding;

    public MacroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMacroBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.macroDietSelect.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.macro_diet_default_button:
                    mCarbsPercent = 0.5;
                    mProteinPercent = 0.3;
                    mFatPercent = 0.2;
                    break;
                case R.id.macro_diet_locarb_button:
                    mCarbsPercent = 0.25;
                    mProteinPercent = 0.3;
                    mFatPercent = 0.45;
                    break;
                case R.id.macro_diet_lofat_button:
                    mCarbsPercent = 0.55;
                    mProteinPercent = 0.3;
                    mFatPercent = 0.15;
                    break;
                case R.id.macro_diet_keto_button:
                    mCarbsPercent = 0.10;
                    mProteinPercent = 0.20;
                    mFatPercent = 0.7;
                    break;
            }
        });

        binding.macroGetResultsButton.setOnClickListener(v -> {
            //Close keyboard
            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

            if (ValidateChoices(view))
            {
                mCalorieNeeds = Integer.parseInt(String.valueOf(binding.macroCalorieEntry.getText()));
                CalculateAndDisplayResults();
            }
        });

        return view;
    }

    private void CalculateAndDisplayResults() {
        binding.macroCarbResult.setText(String.valueOf(Math.round((mCalorieNeeds * mCarbsPercent) / 4)));
        binding.macroProteinResult.setText(String.valueOf(Math.round((mCalorieNeeds * mProteinPercent) / 4)));
        binding.macroFatResult.setText(String.valueOf(Math.round((mCalorieNeeds * mFatPercent) / 9)));
        binding.macroResultsTab.setVisibility(View.VISIBLE);
    }

    private boolean ValidateChoices(View view) {
        RadioButton dietGoal = view.findViewById(binding.macroDietSelect.getCheckedRadioButtonId());

        if (dietGoal == null) {
            Toast.makeText(getContext(), "Please select a diet type", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(binding.macroCalorieEntry.getText())) {
            Toast.makeText(getContext(), "Please enter calorie requirements", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
