package com.example.htlgrk.whattowear;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class G_Settings extends AppCompatActivity {

    Preferences pref;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g_settings_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbarsettings);
        setSupportActionBar(toolbar);
        setTitle("Einstellungen");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(G_Settings.this, G_Uebersicht.class);
                intent.putExtra("preferences", pref);
                startActivity(intent);
            }
        });

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

        final Button btnChangePreferences = (Button) findViewById(R.id.btnChangePreferences);
        btnChangePreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(G_Settings.this, G_Preferences_Jacke.class);
                intent.putExtra("change", true);
                startActivity(intent);
            }
        });

        //SEARCH PREFERENCE
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean currentlocation = settings.getBoolean("currentlocation", true);
        if(currentlocation){
            RadioButton rbCurr = (RadioButton) findViewById(R.id.g_rb_current);
            rbCurr.setChecked(true);
        } else if(!currentlocation){
            RadioButton rbCustom = (RadioButton) findViewById(R.id.g_rb_custom);
            rbCustom.setChecked(true);
            //SHOW CONFIG-OPTIONS
            EditText etLoc = (EditText) findViewById(R.id.g_et_customLoc);
            etLoc.setVisibility(View.VISIBLE);
            Button btnLoc = (Button) findViewById(R.id.g_btn_customLoc);
            btnLoc.setVisibility(View.VISIBLE);

            //SHOW SAVED LOCATION IF AVAILABLE
            SharedPreferences settings1 = PreferenceManager.getDefaultSharedPreferences(this);
            String location = settings1.getString("location", "");
            if(!location.equals("")){
                etLoc.setText(location);
            }
        }

        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.g_rg_loc);
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //CHANGE LOCATION-SEARCH
                EditText etLoc = (EditText) findViewById(R.id.g_et_customLoc);
                etLoc.setVisibility(View.GONE);
                Button btnLoc = (Button) findViewById(R.id.g_btn_customLoc);
                btnLoc.setVisibility(View.GONE);
                if(checkedId == R.id.g_rb_custom){
                    etLoc.setVisibility(View.VISIBLE);
                    btnLoc.setVisibility(View.VISIBLE);

                    SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(G_Settings.this);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("currentlocation", false);
                    editor.commit();

                    //SHOW SAVED LOCATION IF AVAILABLE
                    SharedPreferences settings1 = PreferenceManager.getDefaultSharedPreferences(G_Settings.this);
                    String location = settings1.getString("location", "");
                    if(!location.equals("")){
                        etLoc.setText(location);
                    }
                }else{
                    SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(G_Settings.this);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("currentlocation", true);
                    editor.commit();
                }
            }
        });

        Button btnLoc = (Button) findViewById(R.id.g_btn_customLoc);
        btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLocation();
            }
        });


    }

    private void saveLocation() {
        EditText etLoc = (EditText) findViewById(R.id.g_et_customLoc);
        String loc = etLoc.getText().toString();

        if(!loc.equals("")){
            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(G_Settings.this);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("location", loc);
            editor.commit();

            Intent intent = new Intent(G_Settings.this, G_Uebersicht.class);
            intent.putExtra("preferences", pref);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Eingaben ungültig" , Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(G_Settings.this, G_Uebersicht.class);
        intent.putExtra("preferences", pref);
        startActivity(intent);
    }
}
