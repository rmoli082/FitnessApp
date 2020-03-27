package com.morashstudios.fitnessapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.morashstudios.fitnessapp.database.MeasurementsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeasurementsFragment extends Fragment {

    public MeasurementsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_measurements, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        final MeasurementsAdapter adapter = new MeasurementsAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}
