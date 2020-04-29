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

/**
 * A simple {@link Fragment} subclass.
 */
public class MainScreenFragment extends Fragment {


    public MainScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        CardView bodyfatSelect = view.findViewById(R.id.bodyfat_select);
        CardView bodyindexSelect = view.findViewById(R.id.bodyindex_select);
        CardView calorieSelect = view.findViewById(R.id.calorie_select);
        CardView macroSelect = view.findViewById(R.id.macro_select);
        CardView onermSelect = view.findViewById(R.id.onerm_select);
        CardView measurementSelect = view.findViewById(R.id.measurements_select);

        FragmentManager fm = getFragmentManager();
        final FragmentTransaction transaction = fm.beginTransaction();

        bodyfatSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.contentFragment, new BodyfatCalculatorFragment()).addToBackStack("bodyfat").commit();
            }
        });

        bodyindexSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.contentFragment, new BodyIndexFragment()).addToBackStack("bmi").commit();
            }
        });

        calorieSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.contentFragment, new CalorieFragment()).addToBackStack("calorie").commit();
            }
        });

        macroSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.contentFragment, new MacroFragment()).addToBackStack("macro").commit();
            }
        });

        onermSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.contentFragment, new OneRepMaxFragment()).addToBackStack("repmax").commit();
            }
        });

        measurementSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MeasurementsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
