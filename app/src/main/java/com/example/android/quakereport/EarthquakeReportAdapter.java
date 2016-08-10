package com.example.android.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sanlem on 10.08.16.
 */
public class EarthquakeReportAdapter extends ArrayAdapter<EarthquakeReport> {
    public EarthquakeReportAdapter(Activity context, ArrayList<EarthquakeReport> reports) {
        super(context, 0, reports);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // get the current report
        EarthquakeReport currentReport = getItem(position);

        // find the magnitude view
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        // set the magnitude
        magnitudeView.setText(String.valueOf(currentReport.getMagnitude()));

        // find the magnitude view
        TextView locationView = (TextView) listItemView.findViewById(R.id.location);
        // set the magnitude
        locationView.setText(currentReport.getLocation());

        // find the timestamp view
        TextView timestampView = (TextView) listItemView.findViewById(R.id.timestamp);
        // set the timestamp
        timestampView.setText(currentReport.getTimestamp());

        // return the layout
        return listItemView;

    }
}
