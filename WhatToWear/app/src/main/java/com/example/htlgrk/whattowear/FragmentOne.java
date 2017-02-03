package com.example.htlgrk.whattowear;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;

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
            wd = (WeatherData) getArguments().getSerializable("wd");
            if (wd != null) {
                TextView akt = (TextView) rootView.findViewById(R.id.aktTemp);
                akt.setText(wd.currentTemp+"°C");
                TextView datum = (TextView) rootView.findViewById(R.id.datum);
                SimpleDateFormat df = new SimpleDateFormat("EEEE, dd.MM.yyyy");
                datum.setText(df.format(wd.date)); //FORMAT
                TextView desc = (TextView) rootView.findViewById(R.id.tv_Wetterlage);
                desc.setText(wd.description);
                TextView range = (TextView) rootView.findViewById(R.id.tv_tempRange);
                range.setText(wd.getTempLow() + "°C bis " + wd.getTempHigh() + "°C");
                MyApplication.setClothes(wd.getTempLow(), wd.getTempHigh(), rootView);
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
