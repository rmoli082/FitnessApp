package com.morashstudios.fitnessapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class BodyIndexFragment extends Fragment {

    private double mHeight;
    private double mWeight;
    private double mBodyIndex;

    public BodyIndexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_bodyindex, container, false);

        final EditText weightEntry = view.findViewById(R.id.bmi_weight_entry);
        final EditText heightEntry = view.findViewById(R.id.bmi_height_entry);
        final TextView bmiResults = view.findViewById(R.id.bmi_result);
        final DecimalFormat df = new DecimalFormat("0.0");

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String units = sharedPrefs.getString(getString(R.string.settings_unit_key),
                getString(R.string.settings_unit_default));

        if (units.equals(getString(R.string.settings_unit_metric_value))) {
            weightEntry.setHint(getString(R.string.hint_kg));
            heightEntry.setHint(R.string.hint_cm);
        }

        Button getResults = view.findViewById(R.id.bmi_results_button);

        getResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                view.findViewById(R.id.bmi_results_tab).setVisibility(View.VISIBLE);

                if (String.valueOf(weightEntry.getText()).isEmpty() ||
                String.valueOf(heightEntry.getText()).isEmpty()) {
                    Toast.makeText(getContext(), "Please enter your measurements", Toast.LENGTH_SHORT).show();
                    return;
                }

                mWeight = Double.parseDouble(String.valueOf(weightEntry.getText()));
                mHeight = Double.parseDouble(String.valueOf(heightEntry.getText()));

                if (units.equals(getString(R.string.settings_unit_us_value))) {
                    mWeight /= 2.2;
                    mHeight *= 2.54;
                }

                mHeight /= 100;
                mBodyIndex = mWeight/Math.pow(mHeight, 2);

                bmiResults.setText(df.format(mBodyIndex));
            }
        });

        return view;
    }
}
