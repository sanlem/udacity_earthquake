package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by sanlem on 10.08.16.
 */
public class EarthquakeReportAdapter extends ArrayAdapter<EarthquakeReport> {
    private static final String LOCATION_SEPARATOR = " of ";
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
        magnitudeView.setText(formatMagnitude(currentReport.getMagnitude()));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentReport.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // extract primary location and offset
        String fullLocation = currentReport.getLocation();
        String primaryLocation = "";
        String locationOffset = "";

        if (fullLocation.contains(EarthquakeReportAdapter.LOCATION_SEPARATOR)) {
            String[] parts = fullLocation.split(EarthquakeReportAdapter.LOCATION_SEPARATOR);
            primaryLocation = parts[1];
            locationOffset = parts[0] + EarthquakeReportAdapter.LOCATION_SEPARATOR;
        } else {
            primaryLocation = fullLocation;
            locationOffset = "Near the";
        }

        // find the location_primary view
        TextView locationPrimaryView = (TextView) listItemView.findViewById(R.id.location_primary);
        // set the location
        locationPrimaryView.setText(primaryLocation);

        // find the location_primary view
        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);
        // set the location
        locationOffsetView.setText(locationOffset);

        // find the timestamp view
        TextView timestampView = (TextView) listItemView.findViewById(R.id.timestamp);
        Date dateObject = new Date(currentReport.getTimestamp());
        String formattedDate = formatDate(dateObject);
        formattedDate += "\n" + formatTime(dateObject);
        // set the timestamp
        timestampView.setText(formattedDate);

        // return the layout
        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Return the formatted magnitude string (i.e. "1.1") from a original value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
