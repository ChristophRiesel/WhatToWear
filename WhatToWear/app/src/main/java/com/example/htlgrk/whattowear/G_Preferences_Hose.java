package com.example.htlgrk.whattowear;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import biz.borealis.numberpicker.OnValueChangeListener;

public class G_Preferences_Hose extends AppCompatActivity {

    int value;
    Preferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g_preference_hose);
        pref = new Preferences();

        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        if(params != null){
            pref = (Preferences) params.getSerializable("preferences");
        }



        final com.shawnlin.numberpicker.NumberPicker numberPicker = (com.shawnlin.numberpicker.NumberPicker) findViewById(R.id.npPreferenceHose);
        numberPicker.setValue(20);






















































        Button button = (Button) findViewById(R.id.btnPreferenceHose);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(G_Preferences_Hose.this, G_Preferences_MW.class);


                int valueHose= numberPicker.getValue();
                pref.setValueHose(valueHose);
                intent.putExtra("preferences", pref);
                startActivity(intent);


            }
        });


    }


}
