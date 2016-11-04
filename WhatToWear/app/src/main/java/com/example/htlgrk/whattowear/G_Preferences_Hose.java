package com.example.htlgrk.whattowear;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class G_Preferences_Hose extends AppCompatActivity {

    int valueJacke;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g_preference_hose);

        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        if(params != null){
            valueJacke = params.getInt("jacke");
        }



        final int minValue = -50;
        final int maxValue = 50;
        final NumberPicker numberPicker = (NumberPicker) findViewById(R.id.npPreferenceHose);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(maxValue - minValue);
        numberPicker.setValue(numberPicker.getValue() - minValue);
        numberPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int index) {
                return Integer.toString(index - minValue);
            }
        });


        numberPicker.setBackgroundColor(Color.WHITE);


        Button button = (Button) findViewById(R.id.btnPreferenceHose);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(G_Preferences_Hose.this, G_Preferences_MW.class);
                intent.putExtra("jacke", valueJacke);
                int valueHose = numberPicker.getValue();
                intent.putExtra("hose", valueHose);
                startActivity(intent);


            }
        });


    }


}
