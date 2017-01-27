package com.example.htlgrk.whattowear;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class G_Preferences_Jacke extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g_preference_jacke);


        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        boolean change = false;
        if(params != null){
            change = params.getBoolean("change");
        }
        if(change){
        }else{
            searchFile();
        }


        final com.shawnlin.numberpicker.NumberPicker numberPicker = (com.shawnlin.numberpicker.NumberPicker) findViewById(R.id.npPreferenceJacke);
        numberPicker.setValue(15);


        Button button = (Button) findViewById(R.id.btnPreferenceJacke);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(G_Preferences_Jacke.this, G_Preferences_Hose.class);
                int valueJacke = numberPicker.getValue();
                Preferences pref = new Preferences();
                pref.setValueJacke(valueJacke);
                intent.putExtra("preferences", pref);
                startActivity(intent);

            }
        });

    }

    private void searchFile() {



        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String data = settings.getString("WhatToWear", "");

        if(!data.equals("")){
            String[] x = data.split(";");
            Intent intent = new Intent(G_Preferences_Jacke.this, G_Uebersicht.class);
            int valueJacke = Integer.parseInt(x[0]);
            int valueHose = Integer.parseInt(x[1]);
            String valueGeschlecht = x[2];
            Preferences pref = new Preferences(valueJacke, valueHose, valueGeschlecht);
            intent.putExtra("preferences",  pref);
            startActivity(intent);

        }
    }
}
