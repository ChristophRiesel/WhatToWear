package com.example.htlgrk.whattowear;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


import com.example.htlgrk.whattowear.service.YahooWeatherService;
import com.example.htlgrk.whattowear.service.YahooWheaterCallback;
import com.example.htlgrk.whattowear.wheather_data.Channel;
import com.example.htlgrk.whattowear.wheather_data.Item;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class G_Uebersicht extends AppCompatActivity implements YahooWheaterCallback {

    ViewPager mViewPager;
    Toolbar toolbar;

    public Preferences pref;
    String filename = "WhatToWear.txt";

    WeatherData[] weatherArray;
    Location mLastLocation;

    private YahooWeatherService service;
    private ProgressDialog dialog;

    LocationManager locationManager;
    LocationListener locationListener;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadscreen);

        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        if (params != null) {
            pref = (Preferences) params.getSerializable("preferences");
        }
        writeToFile(pref);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                try {
                    if(location!=null) {
                        mLastLocation = location;
                        List<Address> addresses;
                        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                        addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                        String city = addresses.get(0).getLocality();
                        String state = addresses.get(0).getAdminArea();
                        String country = addresses.get(0).getCountryName();
                        String postalCode = addresses.get(0).getPostalCode();
                        String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

                        //ö ä ü - behandeln
                        country = country.replace("ä","ae");
                        country = country.replace("ö","oe");
                        country = country.replace("ü","ue");
                        country = country.replace("Ä","Ae");
                        country = country.replace("Ö","Oe");
                        country = country.replace("Ü","Ue");


                        String locationstring = city + "," + postalCode + "," + country;


                        service = new YahooWeatherService(G_Uebersicht.this);
                        //dialog = new ProgressDialog(G_Uebersicht.this);
                        //dialog.setMessage("loading");
                        //dialog.show();

                        service.refreshWeather(locationstring);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };


        // Register the listener with the Location Manager to receive location updates
        /*try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        } catch (SecurityException e) {
            e.printStackTrace();
        }*/

        requestGPSLocation();
        if(mLastLocation == null){
            requestNetworkLocation();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menusettings:
                Intent intent = new Intent(G_Uebersicht.this, G_Settings.class);
                intent.putExtra("preferences", pref);
                startActivity(intent);
                break;
            case R.id.menu10days:
                Toast.makeText(G_Uebersicht.this, "10Tage-Übersicht", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void writeToFile(Preferences p) {
        if (p != null) {
            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = settings.edit();
            String line = p.getValueJacke() + ";" + p.getValueHose() + ";" + p.getGeschlecht();
            editor.putString("WhatToWear", line);
            editor.commit();
        }
    }

    @Override
    public void serviceSuccess(Channel channel) {
          Item i = channel.getItem();
        weatherArray = i.getWeatherData();
        //aktuelles Wetterobjekt hat mehr Daten // z.b. aktuelle Temperaturs
        String unit = channel.getUnits().getTemperature();
        weatherArray[0].setCurrentTemp(i.getCondition().getTemperature());
        weatherArray[0].setDescription(i.getCondition().getDescription());
        weatherArray[0].setCode(i.getCondition().getCode());


        setContentView(R.layout.g_uebersicht_screenslide);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //activate Viewapger
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(new PagerAdapter(
                this.getSupportFragmentManager(), this));
        //getSupportFragmentManager().beginTransaction()
              //  .replace(R.id.fragment_container, fragmentOne).commit();
    }

    @Override
    public void serviceFailure(Exception ex) {
        //dialog.dismiss();
        Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
    }


    public class PagerAdapter extends FragmentPagerAdapter {

        G_Uebersicht activity;
        public PagerAdapter(FragmentManager fm, G_Uebersicht activity)
        {
            super(fm);
            this.activity = activity;
        }


        @Override
        public Fragment getItem(int position) {
            /** Show a Fragment based on the position of the current screen */
            if (weatherArray[0] != null) {
                try {
                    locationManager.removeUpdates(locationListener);
                } catch (SecurityException sx) {
                    sx.printStackTrace();
                }
            }
            if (position == 0) {
                Bundle args = new Bundle();
                //WeatherData wd = new WeatherData(10, 15, 5, "cloudy", 30, new Date(), "c");
                args.putSerializable("wd", weatherArray[position]);
                FragmentOne fragment = new FragmentOne();
                fragment.setArguments(args);

                return fragment;
            } else {
                Bundle args = new Bundle();
                //WeatherData wd = new WeatherData(10, 15, 5, "cloudy", 30, new Date(), "c");
                args.putSerializable("wd", weatherArray[position]);
                FragmentTwo fragment = new FragmentTwo();
                fragment.setArguments(args);

                return fragment;
            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
    }


    private void requestGPSLocation(){
        try {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }catch (SecurityException ex){
            ex.printStackTrace();
        }
    }
    private void requestNetworkLocation(){
        try {
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }catch (SecurityException ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        this.finish();
    }


}