package com.example.htlgrk.whattowear.wheather_data;

import com.example.htlgrk.whattowear.WeatherData;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Peter on 04.11.2016.
 */
public class Item implements JSONPopulator {

    private Condition condition;
    private JSONArray forecast;

    private WeatherData[] weatherData;
    int forecastsize = 10;

    public Item() {
        weatherData = new WeatherData[forecastsize];
    }

    public Condition getCondition() {
        return condition;
    }

    public WeatherData[] getWeatherData() {
        return weatherData;
    }

    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));
        forecast = data.optJSONArray("forecast");
        for(int i=0; i<forecastsize; i++) {
            try {
                JSONObject jo = (JSONObject) forecast.get(i);
                int code = Integer.parseInt(jo.getString("code"));

                String dstring = jo.getString("date");
                SimpleDateFormat format = new SimpleDateFormat("d MMM yyyy", Locale.ENGLISH);
                Date date = format.parse(dstring);

                int temphigh = Integer.parseInt(jo.getString("high"));
                int templow = Integer.parseInt(jo.getString("low"));
                String desc = jo.getString("text");

                WeatherData wd = new WeatherData(temphigh, templow, desc, code, date, "c");
                weatherData[i] = wd;
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
