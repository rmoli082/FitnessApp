package com.morashstudios.fitnessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MeasurementListAdapter extends RecyclerView.Adapter<MeasurementListAdapter.MeasurementViewHolder> {

    public interface OnDeleteClickListener {
        void OnDeleteCLickListener(Measurement myMeasurement);
    }

    private final LayoutInflater layoutInflater;
    private List<Measurement> mMeasurements;
    private final OnDeleteClickListener onDeleteClickListener;

    public MeasurementListAdapter(Context context, OnDeleteClickListener listener) {
        layoutInflater = LayoutInflater.from(context);
        this.onDeleteClickListener = listener;

    }

    @NonNull
    @Override
    public MeasurementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new MeasurementViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MeasurementViewHolder holder, int position) {
        if (mMeasurements != null) {
            Measurement measurement = mMeasurements.get(position);
            holder.setData(measurement.toString(), position);
            holder.setListener();
        } else {
            holder.measurementItemView.setText(R.string.no_entries);
        }
    }

    @Override
    public int getItemCount() {
        if (mMeasurements != null) {
            return mMeasurements.size();
        } else return 0;
    }

    public void setMeasurements(List<Measurement> measurements) {
        mMeasurements = measurements;
        notifyDataSetChanged();
    }

    public class MeasurementViewHolder extends RecyclerView.ViewHolder {

        private final TextView measurementItemView;
        private int mPosition;
        private final ImageView deleteButton;

        public MeasurementViewHolder(@NonNull View itemView) {
            super(itemView);
            measurementItemView = itemView.findViewById(R.id.textView);
            deleteButton = itemView.findViewById(R.id.delete_option);
        }

        public void setData(String measurement, int position) {
            measurementItemView.setText(measurement);
            mPosition = position;
        }

        public void setListener() {
            deleteButton.setOnClickListener(v -> {
                if (onDeleteClickListener != null) {
                    onDeleteClickListener.OnDeleteCLickListener(mMeasurements.get(mPosition));
                }
            });
        }
    }
}
