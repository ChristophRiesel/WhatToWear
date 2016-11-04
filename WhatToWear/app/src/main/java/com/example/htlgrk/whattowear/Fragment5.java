package com.example.htlgrk.whattowear;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Christoph on 11.10.2016.
 */
public class Fragment5 extends Fragment{
    View contentView5;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        contentView5 = inflater.inflate(R.layout.fragment5, null);

        Date now = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(now);

        calendar.add(Calendar.DAY_OF_MONTH, 4);

        int month = calendar.get(calendar.MONTH);
        month++;

        TextView tv = (TextView) contentView5.findViewById(R.id.tv_page5);
        tv.setText("Wetterprognose für "+calendar.get(Calendar.DAY_OF_MONTH)+"."+ month);


        return contentView5;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
