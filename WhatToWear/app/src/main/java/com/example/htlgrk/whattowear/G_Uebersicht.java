package com.example.htlgrk.whattowear;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.htlgrk.whattowear.service.YahooWeatherService;
import com.example.htlgrk.whattowear.service.YahooWheaterCallback;
import com.example.htlgrk.whattowear.wheather_data.Channel;
import com.example.htlgrk.whattowear.wheather_data.Item;

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

    String currentCity;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadscreen);

        //CHECK IF CONNECTED
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                while (!isConnectedToInternet(G_Uebersicht.this)) {
                    try {
                        Toast.makeText(G_Uebersicht.this, "Bitte verbinden Sie sich mit dem Internet!", Toast.LENGTH_LONG).show();
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //call onrequestpermissionchangeevent
                ActivityCompat.requestPermissions(G_Uebersicht.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        });



        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        if (params != null) {
            pref = (Preferences) params.getSerializable("preferences");
        }
        writeToFile(pref);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public boolean isConnectedToInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }

        return networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED;
    }


    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    locationManager = (LocationManager) G_Uebersicht.this.getSystemService(Context.LOCATION_SERVICE);
                    locationListener = new LocationListener() {
                        public void onLocationChanged(Location location) {
                            try {
                                if (location != null) {
                                    mLastLocation = location;
                                    List<Address> addresses;
                                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                                    addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                                    String city = addresses.get(0).getLocality();
                                    currentCity = city;
                                    String state = addresses.get(0).getAdminArea();
                                    String country = addresses.get(0).getCountryName();
                                    String postalCode = addresses.get(0).getPostalCode();
                                    String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

                                    //ö ä ü - behandeln
                                    country = country.replace("ä", "ae");
                                    country = country.replace("ö", "oe");
                                    country = country.replace("ü", "ue");
                                    country = country.replace("Ä", "Ae");
                                    country = country.replace("Ö", "Oe");
                                    country = country.replace("Ü", "Ue");


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


                    requestGPSLocation();
                    if (mLastLocation == null) {
                        requestNetworkLocation();
                    }

                } else {
                    Toast.makeText(this, "Ohne Zugriff auf die Position funktioniert die App nicht!", Toast.LENGTH_LONG).show();
                    finish();
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
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

        public PagerAdapter(FragmentManager fm, G_Uebersicht activity) {
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


            if (position == 1) {
                Bundle args = new Bundle();
                args.putSerializable("location", currentCity);
                args.putSerializable("wd", weatherArray[position]);
                FragmentTwo fragment = new FragmentTwo();
                fragment.setArguments(args);

                return fragment;
            } else if (position == 2) {
                Bundle args = new Bundle();
                args.putSerializable("location", currentCity);
                args.putSerializable("wd", weatherArray[position]);
                FragmentThree fragment = new FragmentThree();
                fragment.setArguments(args);

                return fragment;
            } else if (position == 3) {
                Bundle args = new Bundle();
                args.putSerializable("location", currentCity);
                args.putSerializable("wd", weatherArray[position]);
                FragmentFour fragment = new FragmentFour();
                fragment.setArguments(args);

                return fragment;
            } else if (position == 4) {
                Bundle args = new Bundle();
                args.putSerializable("location", currentCity);
                args.putSerializable("wd", weatherArray[position]);
                FragmentFive fragment = new FragmentFive();
                fragment.setArguments(args);

                return fragment;
            } else if (position == 5) {
                Bundle args = new Bundle();
                args.putSerializable("location", currentCity);
                args.putSerializable("wd", weatherArray[position]);
                FragmentSix fragment = new FragmentSix();
                fragment.setArguments(args);

                return fragment;
            } else if (position == 6) {
                Bundle args = new Bundle();
                args.putSerializable("location", currentCity);
                args.putSerializable("wd", weatherArray[position]);
                FragmentSeven fragment = new FragmentSeven();
                fragment.setArguments(args);

                return fragment;
            } else if (position == 7) {
                Bundle args = new Bundle();
                args.putSerializable("location", currentCity);
                args.putSerializable("wd", weatherArray[position]);
                FragmentEight fragment = new FragmentEight();
                fragment.setArguments(args);

                return fragment;
            } else if (position == 8) {
                Bundle args = new Bundle();
                args.putSerializable("location", currentCity);
                args.putSerializable("wd", weatherArray[position]);
                FragmentNine fragment = new FragmentNine();
                fragment.setArguments(args);

                return fragment;
            } else if (position == 9) {
                Bundle args = new Bundle();
                args.putSerializable("location", currentCity);
                args.putSerializable("wd", weatherArray[position]);
                FragmentTen fragment = new FragmentTen();
                fragment.setArguments(args);

                return fragment;
            } else {
                Bundle args = new Bundle();
                //WeatherData wd = new WeatherData(10, 15, 5, "cloudy", 30, new Date(), "c");
                args.putSerializable("location", currentCity);
                args.putSerializable("wd", weatherArray[position]);
                FragmentMain fragment = new FragmentMain();
                fragment.setArguments(args);

                return fragment;

            }
        }

        @Override
        public int getCount() {
            // Show 10 total pages.
            return 10;
        }
    }


    private void requestGPSLocation() {
        try {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        } catch (SecurityException ex) {
            ex.printStackTrace();
        }
    }

    private void requestNetworkLocation() {
        try {
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        } catch (SecurityException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}