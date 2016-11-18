package com.example.htlgrk.whattowear;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Christoph on 08.11.2016.
 */
public class Preferences implements Serializable {

    int valueJacke;
    int valueHose;
    String geschlecht;

    public Preferences(int valueJacke, int valueHose, String geschlecht) {
        this.valueJacke = valueJacke;
        this.valueHose = valueHose;
        this.geschlecht = geschlecht;
    }

    public Preferences() {
    }

    public int getValueHose() {
        return valueHose;
    }

    public void setValueHose(int valueHose) {
        this.valueHose = valueHose;
    }

    public int getValueJacke() {
        return valueJacke;
    }

    public void setValueJacke(int valueJacke) {
        this.valueJacke = valueJacke;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }


}
