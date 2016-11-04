package com.example.htlgrk.whattowear;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StreamCorruptedException;

public class G_Preference_Jacke extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g_preference_jacke);

        searchFile();

        final int minValue = -10;
        final int maxValue = 30;
        final NumberPicker numberPicker = (NumberPicker) findViewById(R.id.npPreferenceJacke);

        numberPicker.setMaxValue(maxValue - minValue);
        numberPicker.setValue(numberPicker.getValue() - minValue);
        numberPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int index) {
                return Integer.toString(index + minValue);
            }
        });


        numberPicker.setBackgroundColor(Color.WHITE);


        Button button = (Button) findViewById(R.id.btnPreferenceJacke);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(G_Preference_Jacke.this, G_Preferences_Hose.class);
                int valueJacke = numberPicker.getValue();
                intent.putExtra("jacke", valueJacke);
                startActivity(intent);

            }
        });

    }

    private void searchFile() {



        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String data = settings.getString("WhatToWear", "");

        if(!data.equals("")){
            String[] x = data.split(";");
            Intent intent = new Intent(G_Preference_Jacke.this, G_Uebersicht.class);
            intent.putExtra("jacke", x[0]);
            intent.putExtra("hose", x[1]);
            intent.putExtra("geschlecht", x[2]);
            startActivity(intent);
        }
    }
}
