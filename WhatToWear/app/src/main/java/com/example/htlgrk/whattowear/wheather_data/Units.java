package com.example.htlgrk.whattowear.wheather_data;

import org.json.JSONObject;

/**
 * Created by Peter on 04.11.2016.
 */
public class Units implements JSONPopulator {

    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature = data.optString("temperature");
    }
}
