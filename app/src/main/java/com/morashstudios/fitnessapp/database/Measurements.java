package com.morashstudios.fitnessapp.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "measurements_table")
public class Measurements {

    @PrimaryKey(autoGenerate = true)
    private int measurement_id;

    private String date;

    @ColumnInfo(name="neck")
    private float neck_circumference;
    @ColumnInfo(name="chest")
    private float chest_circumference;
    @ColumnInfo(name="waist")
    private float waist_circumference;
    @ColumnInfo(name="hip")
    private float hip_circumference;
    @ColumnInfo(name="rbicep")
    private float right_bicep_circumference;
    @ColumnInfo(name="rforearm")
    private float right_forearm_circumference;
    @ColumnInfo(name="lbicep")
    private float left_bicep_circumference;
    @ColumnInfo(name="lforearm")
    private float left_forearm_circumference;
    @ColumnInfo(name="rthigh")
    private float right_thigh_circumference;
    @ColumnInfo(name="rcalf")
    private float right_calf_circumference;
    @ColumnInfo(name="lthigh")
    private float left_thigh_circumference;
    @ColumnInfo(name="lcalf")
    private float left_calf_circumference;
    private float weight;
    private float bf_percent;

    public Measurements(String date, float neck_circumference, float chest_circumference,
                        float waist_circumference, float hip_circumference, float right_bicep_circumference,
                        float right_forearm_circumference, float left_bicep_circumference,
                        float left_forearm_circumference, float right_thigh_circumference,
                        float right_calf_circumference, float left_thigh_circumference,
                        float left_calf_circumference, float weight, float bf_percent) {
        this.date = date;
        this.neck_circumference = neck_circumference;
        this.chest_circumference = chest_circumference;
        this.waist_circumference = waist_circumference;
        this.hip_circumference = hip_circumference;
        this.right_bicep_circumference = right_bicep_circumference;
        this.right_forearm_circumference = right_forearm_circumference;
        this.left_bicep_circumference = left_bicep_circumference;
        this.left_forearm_circumference = left_forearm_circumference;
        this.right_thigh_circumference = right_thigh_circumference;
        this.right_calf_circumference = right_calf_circumference;
        this.left_thigh_circumference = left_thigh_circumference;
        this.left_calf_circumference = left_calf_circumference;
        this.weight = weight;
        this.bf_percent = bf_percent;
    }

    public int getMeasurement_id() {
        return measurement_id;
    }

    public void setMeasurement_id(int id) {
        this.measurement_id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getNeck_circumference() {
        return neck_circumference;
    }

    public void setNeck_circumference(float neck) {
        this.neck_circumference = neck;
    }

    public float getChest_circumference() {
        return chest_circumference;
    }

    public void setChest_circumference(float chest) {
        this.chest_circumference = chest;
    }

    public float getWaist_circumference() {
        return waist_circumference;
    }

    public void setWaist_circumference(float waist) {
        this.waist_circumference = waist;
    }

    public float getHip_circumference() {
        return hip_circumference;
    }

    public void setHip_circumference(float hip) {
        this.hip_circumference = hip;
    }

    public float getRight_bicep_circumference() {
        return right_bicep_circumference;
    }

    public void setRight_bicep_circumference(float right_bicep) {
        this.right_bicep_circumference = right_bicep;
    }

    public float getRight_forearm_circumference() {
        return right_forearm_circumference;
    }

    public void setRight_forearm_circumference(float right_forearm) {
        this.right_forearm_circumference = right_forearm;
    }

    public float getLeft_bicep_circumference() {
        return left_bicep_circumference;
    }

    public void setLeft_bicep_circumference(float left_bicep) {
        this.left_bicep_circumference = left_bicep;
    }

    public float getLeft_forearm_circumference() {
        return left_forearm_circumference;
    }

    public void setLeft_forearm_circumference(float left_forearm) {
        this.left_forearm_circumference = left_forearm;
    }

    public float getRight_thigh_circumference() {
        return right_thigh_circumference;
    }

    public void setRight_thigh_circumference(float right_thigh) {
        this.right_thigh_circumference = right_thigh;
    }

    public float getRight_calf_circumference() {
        return right_calf_circumference;
    }

    public void setRight_calf_circumference(float right_calf) {
        this.right_calf_circumference = right_calf;
    }

    public float getLeft_thigh_circumference() {
        return left_thigh_circumference;
    }

    public void setLeft_thigh_circumference(float left_thigh) {
        this.left_thigh_circumference = left_thigh;
    }

    public float getLeft_calf_circumference() {
        return left_calf_circumference;
    }

    public void setLeft_calf_circumference(float left_calf) {
        this.left_calf_circumference = left_calf;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getBf_percent() {
        return bf_percent;
    }

    public void setBf_percent(float bf_percent) {
        this.bf_percent = bf_percent;
    }

    @Override
    public String toString() {
        return "Measurements{" + date + ": " +
                "Neck: " + neck_circumference + " " +
                "Chest: " + chest_circumference + " " +
                "Waist: " + waist_circumference + " " +
                "Hips: " + hip_circumference + " " +
                "Right Bicep: " + right_bicep_circumference + " " +
                "Right Forearm: " + right_forearm_circumference + " " +
                "Left Bicep: " + left_bicep_circumference + " " +
                "Left Forearm: " + left_forearm_circumference + " " +
                "Right Thigh: " + right_thigh_circumference + " " +
                "Right Calf: " + right_calf_circumference + " " +
                "Left Thigh: " + left_thigh_circumference + " " +
                "Left Calf: " + left_calf_circumference + " " +
                "Weight: " + weight + " " +
                "Body Fat Percentage: " + bf_percent;
    }
}
