package com.example.htlgrk.whattowear;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentOne extends Fragment {

    WeatherData wd;
    int fragposition = 0;

    //OnHeadlineSelectedListener mCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.main_fragment, container,
                false);

        if (getArguments() != null) {
            if (wd != null) {
                WeatherData wdo = (WeatherData) getArguments().getSerializable("wd");

                TextView akt = (TextView) rootView.findViewById(R.id.aktTemp);
                akt.setText(wdo.currentTemp+"");
                TextView datum = (TextView) rootView.findViewById(R.id.datum);
                datum.setText(wdo.date.toString());
                TextView desc = (TextView) rootView.findViewById(R.id.tv_Wetterlage);
                desc.setText(wdo.description);
                TextView range = (TextView) rootView.findViewById(R.id.tv_tempRange);
                range.setText(wdo.getTempLow() + "°C - " + wdo.getTempHigh() + "°C");
                MyApplication.setClothes(wdo.getTempLow(), wdo.getTempHigh(), rootView);
            }
        }

        return rootView;
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnHeadlineSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }


     interface OnHeadlineSelectedListener {
        public void requestWeather(int fragposition);
    }*/


}
