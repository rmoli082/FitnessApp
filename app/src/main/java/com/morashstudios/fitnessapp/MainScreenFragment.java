package com.morashstudios.fitnessapp;


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

        FragmentManager fm = getFragmentManager();
        final FragmentTransaction transaction = fm.beginTransaction();

        bodyfatSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.contentFragment, new BodyfatCalculatorFragment()).commit();
            }
        });

        bodyindexSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.contentFragment, new BodyIndexFragment()).commit();
            }
        });

        calorieSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.contentFragment, new CalorieFragment()).commit();
            }
        });

        macroSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.contentFragment, new MacroFragment()).commit();
            }
        });

        onermSelect.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.contentFragment, new OneRepMaxFragment()).commit();
            }
        }));

        return view;
    }
}
