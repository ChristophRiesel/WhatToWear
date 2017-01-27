package com.example.htlgrk.whattowear;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Peter on 06.12.2016.
 */

public class WeatherData implements Serializable {
    int currentTemp;
    int tempHigh;
    int tempLow;
    String description;
    int code;
    Date date;
    String format;

    public WeatherData(int currentTemp, int tempHigh, int tempLow, String description, int code, Date date, String format) {
        this.currentTemp = currentTemp;
        this.tempHigh = tempHigh;
        this.tempLow = tempLow;
        this.description = description;
        this.code = code;
        this.date = date;
        this.format = format;
    }

    public int getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(int currentTemp) {
        this.currentTemp = currentTemp;
    }

    public int getTempHigh() {
        return tempHigh;
    }

    public void setTempHigh(int tempHigh) {
        this.tempHigh = tempHigh;
    }

    public int getTempLow() {
        return tempLow;
    }

    public void setTempLow(int tempLow) {
        this.tempLow = tempLow;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void changeFormat() {
        if(format=="c"){
            format = "f";
            //UMRECHNUNG VON CELSIUS IN FAHRENHEIT - fehlt
        }else{
            format = "c";
            //UMRECHNUNG VON FAHRENHEIT IN CELSIUS - fehlt
        }
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "currentTemp=" + currentTemp +
                ", tempHigh=" + tempHigh +
                ", tempLow=" + tempLow +
                ", description='" + description + '\'' +
                ", code=" + code +
                ", date=" + date +
                ", format='" + format + '\'' +
                '}';
    }
}
