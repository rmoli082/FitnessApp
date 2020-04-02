package com.morashstudios.fitnessapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class OneRepMaxFragment extends Fragment {

    private float mWeight;
    private int mReps;
    private float mOneRM;


    public OneRepMaxFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one_rep_max, container, false);

        EditText weightEntry = view.findViewById(R.id.repmax_weight_entry);
        EditText repEntry = view.findViewById(R.id.repmax_reps_entry);
        Button getRepMax = view.findViewById(R.id.onerm_results_button);
        TextView oneRMresult = view.findViewById(R.id.repmax_onerm);
        TextView twoRMresult = view.findViewById(R.id.repmax_tworm);
        TextView threeRMresult = view.findViewById(R.id.repmax_threerm);
        TextView fiveRMresult = view.findViewById(R.id.repmax_fiverm);
        TextView tenRMresult = view.findViewById(R.id.repmax_tenrm);

        getRepMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(weightEntry.getText()) || TextUtils.isEmpty(repEntry.getText()) ||
                Integer.parseInt(String.valueOf(repEntry.getText())) == 0) {
                    Toast.makeText(getContext(), "Please enter weight and reps lifted to continue",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                DecimalFormat df = new DecimalFormat("0");
                mWeight = Float.parseFloat(String.valueOf(weightEntry.getText()));
                mReps = Integer.parseInt(String.valueOf(repEntry.getText()));
                mOneRM = mWeight * (1 + (mReps/30.0f));
                oneRMresult.setText(df.format(mOneRM));
                twoRMresult.setText(df.format(mOneRM * 0.95f));
                threeRMresult.setText(df.format(mOneRM * 0.9f));
                fiveRMresult.setText(df.format(mOneRM * 0.86f));
                tenRMresult.setText(df.format(mOneRM * 0.75f));
            }
        });

        return view;
    }
}
