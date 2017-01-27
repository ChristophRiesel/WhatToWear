package com.example.htlgrk.whattowear.service;


import com.example.htlgrk.whattowear.wheather_data.Channel;

/**
 * Created by Peter on 04.11.2016.
 */
public interface YahooWheaterCallback {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception ex);
}
