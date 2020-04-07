package com.morashstudios.fitnessapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

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

    @ColumnInfo(name="date")
    private String mDate;

    @ColumnInfo(name="neck")
    private float mNeck;

    @ColumnInfo(name="chest")
    private float mChest;

    @ColumnInfo(name="waist")
    private float mWaist;

    @ColumnInfo(name="hips")
    private float mHips;

    @ColumnInfo(name="rightBicep")
    private float mRightBicep;

    @ColumnInfo(name="rightForearm")
    private float mRightForearm;

    @ColumnInfo(name="leftBicep")
    private float mLeftBicep;

    @ColumnInfo(name="leftForearm")
    private float mLeftForearm;

    @ColumnInfo(name="rightThigh")
    private float mRightThigh;

    @ColumnInfo(name="rightCalf")
    private float mRightCalf;

    @ColumnInfo(name="leftThigh")
    private float mLeftThigh;

    @ColumnInfo(name="leftCalf")
    private float mLeftCalf;

    @ColumnInfo(name="weight")
    private float mWeight;

    @ColumnInfo(name="bodyFat")
    private float mBodyfat;

    public String getDate() {
        return mDate;
    }


    public float getChest() {
        return mChest;
    }

    public float getWaist() {
        return mWaist;
    }

    public float getHips() {
        return mHips;
    }

    public float getRightBicep() {
        return mRightBicep;
    }

    public float getRightForearm() {
        return mRightForearm;
    }

    public float getLeftBicep() {
        return mLeftBicep;
    }

    public float getLeftForearm() {
        return mLeftForearm;
    }

    public float getRightThigh() {
        return mRightThigh;
    }

    public float getRightCalf() {
        return mRightCalf;
    }

    public float getLeftThigh() {
        return mLeftThigh;
    }

    public float getLeftCalf() {
        return mLeftCalf;
    }

    public void setMeasurements_ID(int measurements_ID) {
        this.measurements_ID = measurements_ID;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public void setNeck(float mNeck) {
        this.mNeck = mNeck;
    }

    public void setChest(float mChest) {
        this.mChest = mChest;
    }

    public void setWaist(float mWaist) {
        this.mWaist = mWaist;
    }

    public void setHips(float mHips) {
        this.mHips = mHips;
    }

    public void setRightBicep(float mRightBicep) {
        this.mRightBicep = mRightBicep;
    }

    public void setRightForearm(float mRightForearm) {
        this.mRightForearm = mRightForearm;
    }

    public void setLeftBicep(float mLeftBicep) {
        this.mLeftBicep = mLeftBicep;
    }

    public void setLeftForearm(float mLeftForearm) {
        this.mLeftForearm = mLeftForearm;
    }

    public void setRightThigh(float mRightThigh) {
        this.mRightThigh = mRightThigh;
    }

    public void setRightCalf(float mRightCalf) {
        this.mRightCalf = mRightCalf;
    }

    public void setLeftThigh(float mLeftThigh) {
        this.mLeftThigh = mLeftThigh;
    }

    public void setLeftCalf(float mLeftCalf) {
        this.mLeftCalf = mLeftCalf;
    }

    public void setWeight(float mWeight) {
        this.mWeight = mWeight;
    }

    public void setBodyfat(float mBodyfat) {
        this.mBodyfat = mBodyfat;
    }

    public float getWeight() {
        return mWeight;
    }

    public float getBodyfat() {
        return mBodyfat;
    }

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

    public Measurement (String date, float neck, float chest, float waist, float hips, float rightBicep,
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
