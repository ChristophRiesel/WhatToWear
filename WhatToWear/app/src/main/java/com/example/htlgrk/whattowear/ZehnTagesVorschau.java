package com.example.htlgrk.whattowear;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class ZehnTagesVorschau extends AppCompatActivity {

    WeatherData[] weatherArray;
    String currentCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zehn_tages_vorschau_layout);

        Bundle b = getIntent().getExtras();
        weatherArray = (WeatherData[]) b.get("weatherArray");
        currentCity = b.getString("currentCity");

        TextView tvCity = (TextView) findViewById(R.id.empfehlung_city);
        tvCity.setText(currentCity);

        MyApplication.set10TagesWetterVorschau(this, weatherArray);




    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        Intent i = new Intent(this, G_Uebersicht.class);
        i.putExtra("weatherArray",weatherArray);
        i.putExtra("currentCity",currentCity);
        startActivity(i);
    }
}
