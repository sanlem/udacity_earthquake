package com.example.android.quakereport;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;

/**
 * Created by sanlem on 14.08.16.
 */
public class EarthquakeLoader extends AsyncTaskLoader<ArrayList<EarthquakeReport>> {
    /** Tag for log messages */
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link EarthquakeLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<EarthquakeReport> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        ArrayList<EarthquakeReport> result = QueryUtils.fetchEarthquakeData(mUrl);
        return result;
    }
}
