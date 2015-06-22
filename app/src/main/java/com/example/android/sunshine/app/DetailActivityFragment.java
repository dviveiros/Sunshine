package com.example.android.sunshine.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private ShareActionProvider mShareActionProvider;

    private static final String LOG_TAG = DetailActivityFragment.class.getSimpleName();
    private static final String HASHTAG = "#Sunshine";
    private String mForecastStr;

    public DetailActivityFragment() {
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        // Inflate menu resource file.
        inflater.inflate(R.menu.menu_detail, menu);
        inflater.inflate(R.menu.detailfragment, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.action_share);

        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent( createShareForecastIntent() );
        } else {
            Log.d(LOG_TAG, "Share provider is null?");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        Intent intent = getActivity().getIntent();
        mForecastStr = intent.getStringExtra(Intent.EXTRA_TEXT);

        TextView selectedForecast = (TextView) view.findViewById(R.id.selected_forecast);
        selectedForecast.setText(mForecastStr);

        return view;
    }

    private Intent createShareForecastIntent() {
        Intent shareIntent = new Intent( Intent.ACTION_SEND );
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra( Intent.EXTRA_TEXT, mForecastStr + " " + HASHTAG);
        return shareIntent;
    }
}
