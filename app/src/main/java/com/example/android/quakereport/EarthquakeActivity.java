/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
//        final ArrayList<EarthquakeReport> earthquakes = new ArrayList<>();
//        earthquakes.add(new EarthquakeReport("San Francisco", 4.2, 12345));
//        earthquakes.add(new EarthquakeReport("San Francisco", 4.2, 12345));
//        earthquakes.add(new EarthquakeReport("San Francisco", 4.2, 12345));
//        earthquakes.add(new EarthquakeReport("San Francisco", 4.2, 12345));
//        earthquakes.add(new EarthquakeReport("San Francisco", 4.2, 12345));
//        earthquakes.add(new EarthquakeReport("San Francisco", 4.2, 12345));
//        earthquakes.add(new EarthquakeReport("San Francisco", 4.2, 12345));
//        earthquakes.add(new EarthquakeReport("San Francisco", 4.2, 12345));
//        earthquakes.add(new EarthquakeReport("San Francisco", 4.2, 12345));
        final ArrayList<EarthquakeReport> earthquakes = QueryUtils.extractEarthquakeReports();

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        EarthquakeReportAdapter itemsAdapter = new EarthquakeReportAdapter(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(itemsAdapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                EarthquakeReport current = earthquakes.get(position);
                Uri webpage = Uri.parse(current.getUrl());
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                // Create intent to show chooser
                Intent chooser = Intent.createChooser(webIntent, "Open in");

                // Verify the intent will resolve to at least one activity
                if (webIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
            }
        });
    }
}
