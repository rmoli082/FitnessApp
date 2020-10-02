package com.morashstudios.fitnessapp;

import android.content.Context;
import android.os.Bundle;
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

import com.morashstudios.fitnessapp.databinding.FragmentMacroBinding;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
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
        final View view = inflater.inflate(R.layout.fragment_macro, container, false);

        binding = FragmentMacroBinding.inflate(getLayoutInflater());

        binding.macroDietSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
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
            }
        });

        binding.macroGetResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
                assert imm != null;
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                RadioButton dietGoal = view.findViewById(binding.macroDietSelect.getCheckedRadioButtonId());

                if (dietGoal == null) {
                    Toast.makeText(getContext(), "Please select a diet type", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (String.valueOf(binding.macroCalorieEntry.getText()).isEmpty()) {
                    Toast.makeText(getContext(), "Please enter calorie requirements", Toast.LENGTH_SHORT).show();
                    return;
                }

                binding.macroResultsTab.setVisibility(View.VISIBLE);

                mCalorieNeeds = Integer.parseInt(String.valueOf(binding.macroCalorieEntry.getText()));

                binding.macroCarbResult.setText(String.valueOf(Math.round((mCalorieNeeds*mCarbsPercent)/4)));
                binding.macroProteinResult.setText(String.valueOf(Math.round((mCalorieNeeds*mProteinPercent)/4)));
                binding.macroFatResult.setText(String.valueOf(Math.round((mCalorieNeeds*mFatPercent)/9)));
            }
        });

        return view;
    }
}
