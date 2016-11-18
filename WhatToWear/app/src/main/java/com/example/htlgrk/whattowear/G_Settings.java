package com.example.htlgrk.whattowear;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class G_Settings extends AppCompatActivity {

    Preferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g__settings_layout);


        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        if(params!= null){
            pref = (Preferences) params.getSerializable("preferences");
        }

        if(pref != null){
            TextView tvgeschlecht = (TextView) findViewById(R.id.tvGeschlechtAnzeige);
            tvgeschlecht.setText(pref.getGeschlecht());
            TextView tvjacke = (TextView) findViewById(R.id.tvJackeAnzeige);
            tvjacke.setText(pref.getValueJacke()+"");
            TextView tvhose = (TextView) findViewById(R.id.tvHoseAnzeige);
            tvhose.setText(pref.getValueHose()+"");
        }else{
            TextView tvgeschlecht = (TextView) findViewById(R.id.tvGeschlechtAnzeige);
            tvgeschlecht.setText("--");
            TextView tvjacke = (TextView) findViewById(R.id.tvJackeAnzeige);
            tvjacke.setText("--");
            TextView tvhose = (TextView) findViewById(R.id.tvHoseAnzeige);
            tvhose.setText("--");
        }

        Button btnChangePreferences = (Button) findViewById(R.id.btnChangePreferences);
        btnChangePreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(G_Settings.this, G_Preferences_Jacke.class);
                intent.putExtra("change", true);
                startActivity(intent);
            }
        });

    }
}
