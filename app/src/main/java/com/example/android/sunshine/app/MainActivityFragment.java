package com.example.android.sunshine.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        //forecast fake data
        List<String> weekForecast = new ArrayList<String>();

        weekForecast.add("Total - Sunny - 20 / 30");
        weekForecast.add("Tomorrow - Cloudy - 15 / 22");
        weekForecast.add("Mon - Sunny - 19 / 27");
        weekForecast.add("Tue - Sunny - 28 / 25");
        weekForecast.add("Wed - Snow - 0 / 5");
        weekForecast.add("Thurs - Cloudy - 5 / 12");
        weekForecast.add("Fri - Sunny - 21 / 25");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.list_item_forecast,R.id.list_item_forecast_textview,weekForecast);

        ListView forecastListView = (ListView) rootView.findViewById(R.id.listview_forecast);
        forecastListView.setAdapter(adapter);

        return rootView;
    }
}
