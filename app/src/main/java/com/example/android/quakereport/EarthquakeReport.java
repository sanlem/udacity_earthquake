package com.example.android.quakereport;

/**
 * Created by sanlem on 10.08.16.
 */
public class EarthquakeReport {
    private String mLocation;
    private double mMagnitude;
    private int mTimestamp;

    public EarthquakeReport(String location, double magnitude, int timestamp) {
        mLocation = location;
        mMagnitude = magnitude;
        mTimestamp = timestamp;
    }

    public String getLocation() {
        return mLocation;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public int getTimestamp() {
        return mTimestamp;
    }
}
