package com.example.htlgrk.whattowear.service;

import android.net.Uri;
import android.os.AsyncTask;


import com.example.htlgrk.whattowear.wheather_data.Channel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Peter on 04.11.2016.
 */
public class YahooWeatherService {
    private YahooWheaterCallback callback;
    private String location;
    private String unit;
    private Exception error;

    public YahooWeatherService(YahooWheaterCallback callback) {
        this.callback = callback;
    }

    public void refreshWeather(String l){
        location = l;
        unit = "c"; //c Celsius, f Fahrenheit

        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\") AND u='" + strings[1] +  "'", strings[0]);
                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));


                try {
                    URL url = new URL(endpoint);
                    URLConnection connection = url.openConnection();

                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while((line = br.readLine())!=null){
                        result.append(line);
                    }

                    return result.toString();
                } catch (Exception e) {
                    error = e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if(s==null && error != null){
                    callback.serviceFailure(error);
                    return;
                }

                try {
                    JSONObject data = new JSONObject(s);
                    JSONObject queryresults = data.optJSONObject("query");
                    int count = queryresults.optInt("count");

                    if(count == 0){
                        callback.serviceFailure(new LocationWheatherException("No wheather information found for "+ location));
                        return;
                    }

                    Channel channel = new Channel();
                    channel.populate(queryresults.optJSONObject("results").optJSONObject("channel"));

                    callback.serviceSuccess(channel);

                } catch (JSONException e) {
                    callback.serviceFailure(e);
                }

            }
        }.execute(location, unit);
    }

    public String getLocation() {
        return location;
    }

}


class LocationWheatherException extends Exception{
   public LocationWheatherException(String detailMessage){
       super(detailMessage);
   }
}