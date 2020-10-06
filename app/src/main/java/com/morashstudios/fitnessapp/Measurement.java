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

    public int getMeasurements_ID() {
        return measurements_ID;
    }

    public void setMeasurements_ID(int measurements_ID) {
        this.measurements_ID = measurements_ID;
    }

    public String getMDate() {
        return mDate;
    }

    public void setMDate(String mDate) {
        this.mDate = mDate;
    }

    public float getMNeck() {
        return mNeck;
    }

    public void setMNeck(float mNeck) {
        this.mNeck = mNeck;
    }

    public float getMChest() {
        return mChest;
    }

    public void setMChest(float mChest) {
        this.mChest = mChest;
    }

    public float getMWaist() {
        return mWaist;
    }

    public void setMWaist(float mWaist) {
        this.mWaist = mWaist;
    }

    public float getMHips() {
        return mHips;
    }

    public void setMHips(float mHips) {
        this.mHips = mHips;
    }

    public float getMRightBicep() {
        return mRightBicep;
    }

    public void setMRightBicep(float mRightBicep) {
        this.mRightBicep = mRightBicep;
    }

    public float getMRightForearm() {
        return mRightForearm;
    }

    public void setMRightForearm(float mRightForearm) {
        this.mRightForearm = mRightForearm;
    }

    public float getMLeftBicep() {
        return mLeftBicep;
    }

    public void setMLeftBicep(float mLeftBicep) {
        this.mLeftBicep = mLeftBicep;
    }

    public float getMLeftForearm() {
        return mLeftForearm;
    }

    public void setMLeftForearm(float mLeftForearm) {
        this.mLeftForearm = mLeftForearm;
    }

    public float getMRightThigh() {
        return mRightThigh;
    }

    public void setMRightThigh(float mRightThigh) {
        this.mRightThigh = mRightThigh;
    }

    public float getMRightCalf() {
        return mRightCalf;
    }

    public void setMRightCalf(float mRightCalf) {
        this.mRightCalf = mRightCalf;
    }

    public float getMLeftThigh() {
        return mLeftThigh;
    }

    public void setMLeftThigh(float mLeftThigh) {
        this.mLeftThigh = mLeftThigh;
    }

    public float getMLeftCalf() {
        return mLeftCalf;
    }

    public void setMLeftCalf(float mLeftCalf) {
        this.mLeftCalf = mLeftCalf;
    }

    public float getMWeight() {
        return mWeight;
    }

    public void setMWeight(float mWeight) {
        this.mWeight = mWeight;
    }

    public float getMBodyfat() {
        return mBodyfat;
    }

    public void setMBodyfat(float mBodyfat) {
        this.mBodyfat = mBodyfat;
    }
}
