package com.morashstudios.fitnessapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "measurements")
public class Measurement {

    @PrimaryKey(autoGenerate = true)
    private int measurements_ID;

    @ColumnInfo(name = "date")
    private String mDate;

    @ColumnInfo(name = "neck")
    private float mNeck;

    @ColumnInfo(name = "chest")
    private float mChest;

    @ColumnInfo(name = "waist")
    private float mWaist;

    @ColumnInfo(name = "hips")
    private float mHips;

    @ColumnInfo(name = "rightBicep")
    private float mRightBicep;

    @ColumnInfo(name = "rightForearm")
    private float mRightForearm;

    @ColumnInfo(name = "leftBicep")
    private float mLeftBicep;

    @ColumnInfo(name = "leftForearm")
    private float mLeftForearm;

    @ColumnInfo(name = "rightThigh")
    private float mRightThigh;

    @ColumnInfo(name = "rightCalf")
    private float mRightCalf;

    @ColumnInfo(name = "leftThigh")
    private float mLeftThigh;

    @ColumnInfo(name = "leftCalf")
    private float mLeftCalf;

    @ColumnInfo(name = "weight")
    private float mWeight;

    @ColumnInfo(name = "bodyFat")
    private float mBodyfat;

    @Override
    public String toString() {
        return "Date: " + mDate + "\n\n" +
                "Neck: " + mNeck + "\t\t" +
                "Chest: " + mChest + "\t\t" +
                "Waist: " + mWaist + "\n" +
                "Hips: " + mHips + "\t\t" +
                "Bicep (r): " + mRightBicep + "\t\t" +
                "Forearm (r): " + mRightForearm + "\n" +
                "Bicep (l): " + mLeftBicep + "\t\t" +
                "Forearm (l): " + mLeftForearm + "\t\t" +
                "Thigh (r): " + mRightThigh + "\n" +
                "Calf (r): " + mRightCalf + "\t\t" +
                "Thigh (l): " + mLeftThigh + "\t\t" +
                "Calf (l): " + mLeftCalf + "\n" +
                "Weight: " + mWeight + "\t\t" +
                "Bodyfat: " + mBodyfat;
    }

    public Measurement(String date, float neck, float chest, float waist, float hips, float rightBicep,
                       float rightForearm, float leftBicep, float leftForearm, float rightThigh,
                       float rightCalf, float leftThigh, float leftCalf, float weight, float bodyfat) {
        this.mDate = date;
        this.mNeck = neck;
        this.mChest = chest;
        this.mWaist = waist;
        this.mHips = hips;
        this.mRightBicep = rightBicep;
        this.mRightForearm = rightForearm;
        this.mLeftBicep = leftBicep;
        this.mLeftForearm = leftForearm;
        this.mRightThigh = rightThigh;
        this.mRightCalf = rightCalf;
        this.mLeftThigh = leftThigh;
        this.mLeftCalf = leftCalf;
        this.mWeight = weight;
        this.mBodyfat = bodyfat;
    }

}
