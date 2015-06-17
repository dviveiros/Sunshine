package com.example.android.sunshine.app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class WeatherDataParser {

    /**
     * Given a string of the form returned by the api call:
     * http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7
     * retrieve the maximum temperature for the day indicated by dayIndex
     * (Note: 0-indexed, so 0 would refer to the first day).
     */
    public static double getMaxTemperatureForDay(String weatherJsonStr, int dayIndex)
            throws JSONException {

        JSONTokener tokener = new JSONTokener(weatherJsonStr);
        JSONObject root = new JSONObject(tokener);

        JSONArray list = root.getJSONArray("list");
        double result = list.getJSONObject(dayIndex).getJSONObject("temp").getDouble("max");

        return result;
    }

}


