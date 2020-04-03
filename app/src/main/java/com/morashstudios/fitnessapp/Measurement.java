package com.morashstudios.fitnessapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "measurements")
public class Measurement {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int measurements_ID;

    public int getMeasurements_ID() {
        return this.measurements_ID;
    }

    public float getNeck() {
        return this.mNeck;
    }

    @NonNull
    @ColumnInfo(name="neck")
    private float mNeck;

    public Measurement (float neck) {
        this.mNeck = neck;
    }

}
