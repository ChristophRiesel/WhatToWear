package com.example.htlgrk.whattowear;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class G_Preferences_MW extends AppCompatActivity {

    int valueJacke;
    int valueHose;
    Preferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pref = new Preferences();
        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        if(params != null){
            pref = (Preferences) params.getSerializable("preferences");
        }



        setContentView(R.layout.g_preferences_mw);
        RadioButton rbFrau = (RadioButton) findViewById(R.id.rbFrau);
        rbFrau.setChecked(true);

        Button button = (Button) findViewById(R.id.btnPreferenceMW);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String geschlecht ="";
                RadioButton rBFrau = (RadioButton) findViewById(R.id.rbFrau);
                RadioButton rBMann = (RadioButton) findViewById(R.id.rbMann);
                if(rBFrau.isChecked())
                    geschlecht = "weiblich";
                else if(rBMann.isChecked())
                    geschlecht = "männlich";

                Intent intent = new Intent(G_Preferences_MW.this, G_Uebersicht.class);
                pref.setGeschlecht(geschlecht);
                intent.putExtra("preferences", pref);
                startActivity(intent);

            }
        });
    }
}
