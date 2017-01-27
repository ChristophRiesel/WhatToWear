package com.example.htlgrk.whattowear.wheather_data;

import org.json.JSONObject;

/**
 * Created by Peter on 04.11.2016.
 */
public class Item implements JSONPopulator {

    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));
    }
}
