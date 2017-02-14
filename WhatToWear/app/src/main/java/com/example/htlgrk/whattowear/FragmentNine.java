package com.example.htlgrk.whattowear;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;

/**
 * Created by Christoph on 03.02.2017.
 */

public class FragmentNine extends Fragment {
    WeatherData wd;
    String currentCity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nine, container,
                false);

        if (getArguments() != null) {
            wd = (WeatherData) getArguments().getSerializable("wd");
            currentCity = (String) getArguments().getSerializable("location");
            if (wd != null) {
                TextView loc = (TextView) rootView.findViewById(R.id.location);
                loc.setText(currentCity);
                TextView datum = (TextView) rootView.findViewById(R.id.datum);
                SimpleDateFormat df = new SimpleDateFormat("EEEE, dd.MM.yyyy");
                datum.setText(df.format(wd.date)); //FORMAT
                TextView desc = (TextView) rootView.findViewById(R.id.tv_Wetterlage);
                desc.setText(MyApplication.getDescriptionForCode(wd.getCode()));
                TextView range = (TextView) rootView.findViewById(R.id.tv_tempRange);
                range.setText(wd.getTempLow() + "°C / " + wd.getTempHigh() + "°C");
                MyApplication.setClothes(wd.getTempLow(), wd.getTempHigh(), rootView, this);
            }
        }

        return rootView;
    }
}
