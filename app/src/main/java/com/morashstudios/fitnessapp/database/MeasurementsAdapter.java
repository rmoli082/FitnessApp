package com.morashstudios.fitnessapp.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.morashstudios.fitnessapp.R;

import java.util.List;

public class MeasurementsAdapter extends RecyclerView.Adapter<MeasurementsAdapter.MeasurementsViewHolder> {

    class MeasurementsViewHolder extends RecyclerView.ViewHolder {

        private final TextView measurementsItemView;

        private MeasurementsViewHolder(View itemView) {
            super(itemView);
            measurementsItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Measurements> mMeasurements;

    public MeasurementsAdapter(Context context) {mInflater = LayoutInflater.from(context); }

    @Override
    public MeasurementsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new MeasurementsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MeasurementsViewHolder holder, int position) {
        if (mMeasurements != null) {
            Measurements current = mMeasurements.get(position);
            holder.measurementsItemView.setText(current.toString());
        } else {
            holder.measurementsItemView.setText("No measurements");
        }
    }

    public void setMeasurements(List<Measurements> measurements) {
        mMeasurements = measurements;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mMeasurements != null) {
            return mMeasurements.size();
        } else {
            return 0;
        }
    }
}
