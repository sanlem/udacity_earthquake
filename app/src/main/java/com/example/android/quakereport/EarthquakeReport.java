package com.example.android.quakereport;

/**
 * Created by sanlem on 10.08.16.
 */
public class EarthquakeReport {
    private String mLocation;
    private double mMagnitude;
    private long mTimestamp;
    private String mUrl;

    public EarthquakeReport(String location, double magnitude, long timestamp, String url) {
        mLocation = location;
        mMagnitude = magnitude;
        mTimestamp = timestamp;
        mUrl = url;
    }

    public String getLocation() {
        return mLocation;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public long getTimestamp() {
        return mTimestamp;
    }

    public String getUrl() { return mUrl; }
}
