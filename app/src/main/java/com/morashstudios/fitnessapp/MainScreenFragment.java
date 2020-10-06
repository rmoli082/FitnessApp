package com.morashstudios.fitnessapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.morashstudios.fitnessapp.databinding.FragmentMainBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainScreenFragment extends Fragment {

    private FragmentMainBinding binding;


    public MainScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        FragmentManager fm = getActivity().getSupportFragmentManager();
        final FragmentTransaction transaction = fm.beginTransaction();

        binding.bodyfatSelect.setOnClickListener(v -> transaction.replace(R.id.contentFragment, new BodyFatCalculatorFragment()).addToBackStack("bodyfat").commit());

        binding.bodyindexSelect.setOnClickListener(v -> transaction.replace(R.id.contentFragment, new BodyIndexFragment()).addToBackStack("bmi").commit());

        binding.calorieSelect.setOnClickListener(v -> transaction.replace(R.id.contentFragment, new CalorieFragment()).addToBackStack("calorie").commit());

        binding.macroSelect.setOnClickListener(v -> transaction.replace(R.id.contentFragment, new MacroFragment()).addToBackStack("macro").commit());

        binding.onermSelect.setOnClickListener(v -> transaction.replace(R.id.contentFragment, new OneRepMaxFragment()).addToBackStack("repmax").commit());

        binding.measurementsSelect.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MeasurementsActivity.class);
            startActivity(intent);
        });

        return view;
    }
}
