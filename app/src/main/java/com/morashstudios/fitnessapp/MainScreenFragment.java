package com.morashstudios.fitnessapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
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
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        binding = FragmentMainBinding.inflate(getLayoutInflater());

        FragmentManager fm = getActivity().getSupportFragmentManager();
        final FragmentTransaction transaction = fm.beginTransaction();

        binding.bodyfatSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.contentFragment, new BodyfatCalculatorFragment()).addToBackStack("bodyfat").commit();
            }
        });

        binding.bodyindexSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.contentFragment, new BodyIndexFragment()).addToBackStack("bmi").commit();
            }
        });

        binding.calorieSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.contentFragment, new CalorieFragment()).addToBackStack("calorie").commit();
            }
        });

        binding.macroSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.contentFragment, new MacroFragment()).addToBackStack("macro").commit();
            }
        });

        binding.onermSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.contentFragment, new OneRepMaxFragment()).addToBackStack("repmax").commit();
            }
        });

        binding.measurementsSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MeasurementsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
