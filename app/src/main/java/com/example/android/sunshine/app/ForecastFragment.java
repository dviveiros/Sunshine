package com.example.android.sunshine.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastFragment extends Fragment {

    /** Adapter to deal with weather forecasts */
    private ArrayAdapter<String> adapter;

    public ForecastFragment() {

    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu( Menu menu, MenuInflater inflater ) {
        inflater.inflate(R.menu.forecastfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        int id = item.getItemId();

        if ( id == R.id.action_refresh ) {
            updateWeather();
            return true;
        } else if ( id == R.id.action_view_location ) {
            showMap();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateWeather();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.list_item_forecast,R.id.list_item_forecast_textview);

        ListView forecastListView = (ListView) rootView.findViewById(R.id.listview_forecast);
        forecastListView.setAdapter(adapter);

        updateWeather();

        forecastListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = (TextView) view;
                Log.v("Teste", "View = " + textView.getText());
                //Toast toast = Toast.makeText(view.getContext(), textView.getText(), Toast.LENGTH_SHORT);
                //toast.show();

                Intent detailIntent = new Intent(getActivity(), DetailActivity.class).putExtra(
                        Intent.EXTRA_TEXT, textView.getText());
                startActivity(detailIntent);
            }
        });

        return rootView;
    }

    /**
     * Updates weather
     */
    private void updateWeather() {
        //gets the postal code
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(
                this.getActivity().getBaseContext());

        String postalCode = prefs.getString(
                getResources().getString(R.string.pref_location_key),
                getResources().getString(R.string.pref_location_default));

        String metricUnit = prefs.getString(
                getResources().getString(R.string.pref_temp_unit_key),
                getResources().getString(R.string.pref_temp_unit_default));


        FetchWeatherTask fetchWeatherTask = new FetchWeatherTask( getActivity(), adapter);
        fetchWeatherTask.execute(postalCode, metricUnit);
    }

    /**
     * Show the map
     */
    private void showMap() {

        //gets the postal code
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(
                this.getActivity().getBaseContext());

        String postalCode = prefs.getString(
                getResources().getString(R.string.pref_location_key),
                getResources().getString(R.string.pref_location_default));

        Uri geoLocation = Uri.parse("geo:0,0?").buildUpon().appendQueryParameter("q", postalCode)
                .build();
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
        intent.setData(geoLocation);

        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
