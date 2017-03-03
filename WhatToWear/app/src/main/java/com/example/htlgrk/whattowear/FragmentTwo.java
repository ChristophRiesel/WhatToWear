package com.example.htlgrk.whattowear;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spyhunter99.supertooltips.ToolTip;
import com.spyhunter99.supertooltips.ToolTipManager;

import java.text.SimpleDateFormat;

public class FragmentTwo extends Fragment {
    WeatherData wd;
    String currentCity;
    public ToolTipManager tooltips;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_two, container,
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

        tooltips = new ToolTipManager(getActivity());

        TextView tv = (TextView) rootView.findViewById(R.id.location);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToolTip toolTip = new ToolTip()
                        .withText(currentCity)
                        .withTextColor(Color.WHITE)
                        .withColor(Color.rgb(64, 81, 181)) //or whatever you want
                        .withAnimationType(ToolTip.AnimationType.FROM_MASTER_VIEW)
                        .withShadow();
                tooltips.showToolTip(toolTip, v);
            }
        });

        return rootView;
    }
}
