package com.example.htlgrk.whattowear;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

/**
 * Created by Christoph on 13.01.2017.
 */
public class MyApplication extends Application {


    public static void setClothes(int low, int high, View mainView, Fragment fragment) {
        Preferences pref = null;
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(fragment.getContext());
        String data = settings.getString("WhatToWear", "");

        if (!data.equals("")) {
            String[] x = data.split(";");
            int jacke = Integer.parseInt(x[0]);
            int hose = Integer.parseInt(x[1]);
            String geschlecht = x[2];
            pref = new Preferences(jacke, hose, geschlecht);
        }


        int mintemp = low;
        int maxtemp = high;

        float x = (maxtemp - mintemp) / 2.5f;
        int val = Math.round(x);
        int value = mintemp + val;

        int temp = value;
        if (pref != null) {
            int valuejacke = pref.getValueJacke();
            int valuehose = pref.getValueHose();


            Kleidungstyp hose = Kleidungstyp.LANGEHOSE;
            Kleidungstyp jacke = Kleidungstyp.DICKEJACKE;
            Kleidungstyp oberteil = Kleidungstyp.PULLOVER;

            ImageView ivMann = (ImageView) mainView.findViewById(R.id.iv_mann);
            ivMann.setVisibility(View.VISIBLE);


            TextView tvJacke = (TextView) mainView.findViewById(R.id.tvJacke);
            TextView tvOberteil = (TextView) mainView.findViewById(R.id.tvOberteil);
            TextView tvHose = (TextView) mainView.findViewById(R.id.tvHose);
            ImageView ivLangeHose = (ImageView) mainView.findViewById(R.id.iv_langehose);
            ivLangeHose.setVisibility(View.GONE);
            ImageView ivkurzeHose = (ImageView) mainView.findViewById(R.id.iv_kurzehose);
            ivkurzeHose.setVisibility(View.GONE);
            ImageView ivTshirt = (ImageView) mainView.findViewById(R.id.iv_tshirt);
            ImageView ivPullover = (ImageView) mainView.findViewById(R.id.iv_pullover);
            ivPullover.setVisibility(View.GONE);
            ivTshirt.setVisibility(View.GONE);
            ImageView ivDuenneJacke = (ImageView) mainView.findViewById(R.id.iv_duennejacke);
            ivDuenneJacke.setVisibility(View.GONE);
            ImageView ivDickeJacke = (ImageView) mainView.findViewById(R.id.iv_dickejacke);
            ivDickeJacke.setVisibility(View.GONE);

            //HOSE
            if (temp >= valuehose) {
                hose = Kleidungstyp.KURZEHOSE;
            } else {
                hose = Kleidungstyp.LANGEHOSE;
            }

            //JACKE
            if (temp >= valuejacke + 6) {
                oberteil= Kleidungstyp.TSHIRT;
                jacke = Kleidungstyp.KEINEJACKE;
            } else if (temp > valuejacke) {
                oberteil = Kleidungstyp.TSHIRT;
                jacke = Kleidungstyp.PULLOVER;
            } else if (temp <= valuejacke) {
                oberteil = Kleidungstyp.TSHIRT;
                jacke = Kleidungstyp.DUENNEJACKE;
            } else if (temp <= valuejacke - 4) {
                oberteil = Kleidungstyp.PULLOVER;
                jacke = Kleidungstyp.DUENNEJACKE;
            } else if (temp <= valuejacke - 8) {
                oberteil= Kleidungstyp.PULLOVER;
                jacke = Kleidungstyp.DICKEJACKE;
            }

            //HOSE
            switch (hose) {
                case LANGEHOSE:
                    //Zeige Bild für lange Hose an
                    ivLangeHose.setVisibility(View.VISIBLE);
                    tvHose.setText("Lange Hose");
                    break;
                case KURZEHOSE:
                    //Zeige Bild für kurze Hose an
                    ivkurzeHose.setVisibility(View.VISIBLE);
                    tvHose.setText("Kurze Hose");
                    break;

            }

            //OBERTEIL UND JACKE
            if(oberteil == Kleidungstyp.TSHIRT && jacke == Kleidungstyp.KEINEJACKE){
                ivTshirt.setVisibility(View.VISIBLE);
                tvOberteil.setText("T-Shirt");
                tvJacke.setText("Keine Jacke");
            }else if(oberteil == Kleidungstyp.TSHIRT && jacke == Kleidungstyp.PULLOVER){
                ivTshirt.setVisibility(View.VISIBLE);
                ivPullover.setVisibility(View.VISIBLE);
                tvOberteil.setText("T-Shirt");
                tvJacke.setText("Pullover");
            }else if(oberteil == Kleidungstyp.TSHIRT && jacke == Kleidungstyp.DUENNEJACKE){
                ivTshirt.setVisibility(View.VISIBLE);
                ivDuenneJacke.setVisibility(View.VISIBLE);
                tvOberteil.setText("T-Shirt");
                tvJacke.setText("dünne Jacke");
            }else if(oberteil == Kleidungstyp.PULLOVER && jacke == Kleidungstyp.DUENNEJACKE){
                ivTshirt.setVisibility(View.VISIBLE);
                ivTshirt.setVisibility(View.VISIBLE);
                ivDuenneJacke.setVisibility(View.VISIBLE);
                tvOberteil.setText("Pullover");
                tvJacke.setText("dünne Jacke");
            }else if(oberteil == Kleidungstyp.PULLOVER && jacke == Kleidungstyp.DICKEJACKE){
                ivTshirt.setVisibility(View.VISIBLE);
                ivPullover.setVisibility(View.VISIBLE);
                ivDickeJacke.setVisibility(View.VISIBLE);
                tvOberteil.setText("T-Shirt & Pullover");
                tvJacke.setText("dicke Jacke");
            }
        }
    }


    public static void set10TagesWetterVorschau(ZehnTagesVorschau main, WeatherData[] weatherArray) {

        SimpleDateFormat df = new SimpleDateFormat("EEEE, dd.MM.yyyy");

        TextView tvJacke;
        TextView tvOberteil;
        TextView tvHose;
        ImageView ivMann;
        ImageView ivLangeHose;
        ImageView ivkurzeHose;
        ImageView ivTshirt;
        ImageView ivPullover;
        ImageView ivDuenneJacke;
        ImageView ivDickeJacke;

        TextView tvDatum;

        //FELD 1
        tvDatum = (TextView) main.findViewById(R.id.datum1);
        WeatherData wd1 = weatherArray[0];
        tvDatum.setText(df.format(wd1.date)); //FORMAT
        tvJacke = (TextView) main.findViewById(R.id.tvJacke1);
        tvOberteil = (TextView) main.findViewById(R.id.tvOberteil1);
        tvHose = (TextView) main.findViewById(R.id.tvHose1);
        ivMann = (ImageView) main.findViewById(R.id.iv_mann1);
        ivLangeHose = (ImageView) main.findViewById(R.id.iv_langehose1);
        ivkurzeHose = (ImageView) main.findViewById(R.id.iv_kurzehose1);
        ivTshirt = (ImageView) main.findViewById(R.id.iv_tshirt1);
        ivPullover = (ImageView) main.findViewById(R.id.iv_pullover1);
        ivDuenneJacke = (ImageView) main.findViewById(R.id.iv_duennejacke1);
        ivDickeJacke = (ImageView) main.findViewById(R.id.iv_dickejacke1);
        setUbersichtClothes(wd1.tempLow, wd1.tempHigh, main, tvJacke, tvOberteil, tvHose, ivMann, ivLangeHose,
                ivkurzeHose, ivTshirt, ivPullover, ivDuenneJacke, ivDickeJacke);

        //FELD 2
        tvDatum = (TextView) main.findViewById(R.id.datum2);
        WeatherData wd2 = weatherArray[1];
        tvDatum.setText(df.format(wd2.date)); //FORMAT
        tvJacke = (TextView) main.findViewById(R.id.tvJacke2);
        tvOberteil = (TextView) main.findViewById(R.id.tvOberteil2);
        tvHose = (TextView) main.findViewById(R.id.tvHose2);
        ivMann = (ImageView) main.findViewById(R.id.iv_mann2);
        ivLangeHose = (ImageView) main.findViewById(R.id.iv_langehose2);
        ivkurzeHose = (ImageView) main.findViewById(R.id.iv_kurzehose2);
        ivTshirt = (ImageView) main.findViewById(R.id.iv_tshirt2);
        ivPullover = (ImageView) main.findViewById(R.id.iv_pullover2);
        ivDuenneJacke = (ImageView) main.findViewById(R.id.iv_duennejacke2);
        ivDickeJacke = (ImageView) main.findViewById(R.id.iv_dickejacke2);
        setUbersichtClothes(wd2.tempLow, wd2.tempHigh, main, tvJacke, tvOberteil, tvHose, ivMann, ivLangeHose,
                ivkurzeHose, ivTshirt, ivPullover, ivDuenneJacke, ivDickeJacke);

        //FELD 3
        tvDatum = (TextView) main.findViewById(R.id.datum3);
        WeatherData wd3 = weatherArray[2];
        tvDatum.setText(df.format(wd3.date)); //FORMAT
        tvJacke = (TextView) main.findViewById(R.id.tvJacke3);
        tvOberteil = (TextView) main.findViewById(R.id.tvOberteil3);
        tvHose = (TextView) main.findViewById(R.id.tvHose3);
        ivMann = (ImageView) main.findViewById(R.id.iv_mann2);
        ivLangeHose = (ImageView) main.findViewById(R.id.iv_langehose3);
        ivkurzeHose = (ImageView) main.findViewById(R.id.iv_kurzehose3);
        ivTshirt = (ImageView) main.findViewById(R.id.iv_tshirt3);
        ivPullover = (ImageView) main.findViewById(R.id.iv_pullover3);
        ivDuenneJacke = (ImageView) main.findViewById(R.id.iv_duennejacke3);
        ivDickeJacke = (ImageView) main.findViewById(R.id.iv_dickejacke3);
        setUbersichtClothes(wd3.tempLow, wd3.tempHigh, main, tvJacke, tvOberteil, tvHose, ivMann, ivLangeHose,
                ivkurzeHose, ivTshirt, ivPullover, ivDuenneJacke, ivDickeJacke);

        //FELD 4
        tvDatum = (TextView) main.findViewById(R.id.datum4);
        WeatherData wd4 = weatherArray[3];
        tvDatum.setText(df.format(wd4.date)); //FORMAT
        tvJacke = (TextView) main.findViewById(R.id.tvJacke4);
        tvOberteil = (TextView) main.findViewById(R.id.tvOberteil4);
        tvHose = (TextView) main.findViewById(R.id.tvHose4);
        ivMann = (ImageView) main.findViewById(R.id.iv_mann4);
        ivLangeHose = (ImageView) main.findViewById(R.id.iv_langehose4);
        ivkurzeHose = (ImageView) main.findViewById(R.id.iv_kurzehose4);
        ivTshirt = (ImageView) main.findViewById(R.id.iv_tshirt4);
        ivPullover = (ImageView) main.findViewById(R.id.iv_pullover4);
        ivDuenneJacke = (ImageView) main.findViewById(R.id.iv_duennejacke4);
        ivDickeJacke = (ImageView) main.findViewById(R.id.iv_dickejacke4);
        setUbersichtClothes(wd4.tempLow, wd4.tempHigh, main, tvJacke, tvOberteil, tvHose, ivMann, ivLangeHose,
                ivkurzeHose, ivTshirt, ivPullover, ivDuenneJacke, ivDickeJacke);

        //FELD 5
        tvDatum = (TextView) main.findViewById(R.id.datum5);
        WeatherData wd5 = weatherArray[4];
        tvDatum.setText(df.format(wd5.date)); //FORMAT
        tvJacke = (TextView) main.findViewById(R.id.tvJacke5);
        tvOberteil = (TextView) main.findViewById(R.id.tvOberteil5);
        tvHose = (TextView) main.findViewById(R.id.tvHose5);
        ivMann = (ImageView) main.findViewById(R.id.iv_mann5);
        ivLangeHose = (ImageView) main.findViewById(R.id.iv_langehose5);
        ivkurzeHose = (ImageView) main.findViewById(R.id.iv_kurzehose5);
        ivTshirt = (ImageView) main.findViewById(R.id.iv_tshirt5);
        ivPullover = (ImageView) main.findViewById(R.id.iv_pullover5);
        ivDuenneJacke = (ImageView) main.findViewById(R.id.iv_duennejacke5);
        ivDickeJacke = (ImageView) main.findViewById(R.id.iv_dickejacke5);
        setUbersichtClothes(wd5.tempLow, wd5.tempHigh, main, tvJacke, tvOberteil, tvHose, ivMann, ivLangeHose,
                ivkurzeHose, ivTshirt, ivPullover, ivDuenneJacke, ivDickeJacke);

        //FELD 6
        tvDatum = (TextView) main.findViewById(R.id.datum6);
        WeatherData wd6 = weatherArray[5];
        tvDatum.setText(df.format(wd6.date)); //FORMAT
        tvJacke = (TextView) main.findViewById(R.id.tvJacke6);
        tvOberteil = (TextView) main.findViewById(R.id.tvOberteil6);
        tvHose = (TextView) main.findViewById(R.id.tvHose6);
        ivMann = (ImageView) main.findViewById(R.id.iv_mann6);
        ivLangeHose = (ImageView) main.findViewById(R.id.iv_langehose6);
        ivkurzeHose = (ImageView) main.findViewById(R.id.iv_kurzehose6);
        ivTshirt = (ImageView) main.findViewById(R.id.iv_tshirt6);
        ivPullover = (ImageView) main.findViewById(R.id.iv_pullover6);
        ivDuenneJacke = (ImageView) main.findViewById(R.id.iv_duennejacke6);
        ivDickeJacke = (ImageView) main.findViewById(R.id.iv_dickejacke6);
        setUbersichtClothes(wd6.tempLow, wd6.tempHigh, main, tvJacke, tvOberteil, tvHose, ivMann, ivLangeHose,
                ivkurzeHose, ivTshirt, ivPullover, ivDuenneJacke, ivDickeJacke);

        //FELD 7
        tvDatum = (TextView) main.findViewById(R.id.datum7);
        WeatherData wd7 = weatherArray[6];
        tvDatum.setText(df.format(wd7.date)); //FORMAT
        tvJacke = (TextView) main.findViewById(R.id.tvJacke7);
        tvOberteil = (TextView) main.findViewById(R.id.tvOberteil7);
        tvHose = (TextView) main.findViewById(R.id.tvHose7);
        ivMann = (ImageView) main.findViewById(R.id.iv_mann7);
        ivLangeHose = (ImageView) main.findViewById(R.id.iv_langehose7);
        ivkurzeHose = (ImageView) main.findViewById(R.id.iv_kurzehose7);
        ivTshirt = (ImageView) main.findViewById(R.id.iv_tshirt7);
        ivPullover = (ImageView) main.findViewById(R.id.iv_pullover7);
        ivDuenneJacke = (ImageView) main.findViewById(R.id.iv_duennejacke7);
        ivDickeJacke = (ImageView) main.findViewById(R.id.iv_dickejacke7);
        setUbersichtClothes(wd7.tempLow, wd7.tempHigh, main, tvJacke, tvOberteil, tvHose, ivMann, ivLangeHose,
                ivkurzeHose, ivTshirt, ivPullover, ivDuenneJacke, ivDickeJacke);

        //FELD 8
        tvDatum = (TextView) main.findViewById(R.id.datum8);
        WeatherData wd8 = weatherArray[7];
        tvDatum.setText(df.format(wd8.date)); //FORMAT
        tvJacke = (TextView) main.findViewById(R.id.tvJacke8);
        tvOberteil = (TextView) main.findViewById(R.id.tvOberteil8);
        tvHose = (TextView) main.findViewById(R.id.tvHose8);
        ivMann = (ImageView) main.findViewById(R.id.iv_mann8);
        ivLangeHose = (ImageView) main.findViewById(R.id.iv_langehose8);
        ivkurzeHose = (ImageView) main.findViewById(R.id.iv_kurzehose8);
        ivTshirt = (ImageView) main.findViewById(R.id.iv_tshirt8);
        ivPullover = (ImageView) main.findViewById(R.id.iv_pullover8);
        ivDuenneJacke = (ImageView) main.findViewById(R.id.iv_duennejacke8);
        ivDickeJacke = (ImageView) main.findViewById(R.id.iv_dickejacke8);
        setUbersichtClothes(wd8.tempLow, wd8.tempHigh, main, tvJacke, tvOberteil, tvHose, ivMann, ivLangeHose,
                ivkurzeHose, ivTshirt, ivPullover, ivDuenneJacke, ivDickeJacke);

        //FELD 9
        tvDatum = (TextView) main.findViewById(R.id.datum9);
        WeatherData wd9 = weatherArray[8];
        tvDatum.setText(df.format(wd9.date)); //FORMAT
        tvJacke = (TextView) main.findViewById(R.id.tvJacke9);
        tvOberteil = (TextView) main.findViewById(R.id.tvOberteil9);
        tvHose = (TextView) main.findViewById(R.id.tvHose9);
        ivMann = (ImageView) main.findViewById(R.id.iv_mann9);
        ivLangeHose = (ImageView) main.findViewById(R.id.iv_langehose9);
        ivkurzeHose = (ImageView) main.findViewById(R.id.iv_kurzehose9);
        ivTshirt = (ImageView) main.findViewById(R.id.iv_tshirt9);
        ivPullover = (ImageView) main.findViewById(R.id.iv_pullover9);
        ivDuenneJacke = (ImageView) main.findViewById(R.id.iv_duennejacke9);
        ivDickeJacke = (ImageView) main.findViewById(R.id.iv_dickejacke9);
        setUbersichtClothes(wd9.tempLow, wd9.tempHigh, main, tvJacke, tvOberteil, tvHose, ivMann, ivLangeHose,
                ivkurzeHose, ivTshirt, ivPullover, ivDuenneJacke, ivDickeJacke);

        //FELD 2
        tvDatum = (TextView) main.findViewById(R.id.datum10);
        WeatherData wd10 = weatherArray[9];
        tvDatum.setText(df.format(wd10.date)); //FORMAT
        tvJacke = (TextView) main.findViewById(R.id.tvJacke10);
        tvOberteil = (TextView) main.findViewById(R.id.tvOberteil10);
        tvHose = (TextView) main.findViewById(R.id.tvHose10);
        ivMann = (ImageView) main.findViewById(R.id.iv_mann10);
        ivLangeHose = (ImageView) main.findViewById(R.id.iv_langehose10);
        ivkurzeHose = (ImageView) main.findViewById(R.id.iv_kurzehose10);
        ivTshirt = (ImageView) main.findViewById(R.id.iv_tshirt10);
        ivPullover = (ImageView) main.findViewById(R.id.iv_pullover10);
        ivDuenneJacke = (ImageView) main.findViewById(R.id.iv_duennejacke10);
        ivDickeJacke = (ImageView) main.findViewById(R.id.iv_dickejacke10);
        setUbersichtClothes(wd10.tempLow, wd10.tempHigh, main, tvJacke, tvOberteil, tvHose, ivMann, ivLangeHose,
                ivkurzeHose, ivTshirt, ivPullover, ivDuenneJacke, ivDickeJacke);

    }

    public static void setUbersichtClothes(int low, int high, ZehnTagesVorschau main, TextView tvJacke, TextView tvOberteil,
                                           TextView tvHose, ImageView ivMann, ImageView ivLangeHose, ImageView ivkurzeHose, ImageView ivTshirt,
                                           ImageView ivPullover, ImageView ivDuenneJacke, ImageView ivDickeJacke) {
        Preferences pref = null;
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(main.getApplicationContext());
        String data = settings.getString("WhatToWear", "");

        if (!data.equals("")) {
            String[] x = data.split(";");
            int jacke = Integer.parseInt(x[0]);
            int hose = Integer.parseInt(x[1]);
            String geschlecht = x[2];
            pref = new Preferences(jacke, hose, geschlecht);
        }


        int mintemp = low;
        int maxtemp = high;

        float x = (maxtemp - mintemp) / 2.5f;
        int val = Math.round(x);
        int value = mintemp + val;

        int temp = value;
        if (pref != null) {
            int valuejacke = pref.getValueJacke();
            int valuehose = pref.getValueHose();

            Kleidungstyp hose = Kleidungstyp.LANGEHOSE;
            Kleidungstyp jacke = Kleidungstyp.DICKEJACKE;
            Kleidungstyp oberteil = Kleidungstyp.PULLOVER;

            ivMann.setVisibility(View.VISIBLE);
            ivLangeHose.setVisibility(View.GONE);
            ivkurzeHose.setVisibility(View.GONE);
            ivPullover.setVisibility(View.GONE);
            ivTshirt.setVisibility(View.GONE);
            ivDuenneJacke.setVisibility(View.GONE);
            ivDickeJacke.setVisibility(View.GONE);

            //HOSE
            if (temp >= valuehose) {
                hose = Kleidungstyp.KURZEHOSE;
            } else {
                hose = Kleidungstyp.LANGEHOSE;
            }

            //JACKE
            if (temp >= valuejacke + 6) {
                oberteil= Kleidungstyp.TSHIRT;
                jacke = Kleidungstyp.KEINEJACKE;
            } else if (temp > valuejacke) {
                oberteil = Kleidungstyp.TSHIRT;
                jacke = Kleidungstyp.PULLOVER;
            } else if (temp <= valuejacke) {
                oberteil = Kleidungstyp.TSHIRT;
                jacke = Kleidungstyp.DUENNEJACKE;
            } else if (temp <= valuejacke - 4) {
                oberteil = Kleidungstyp.PULLOVER;
                jacke = Kleidungstyp.DUENNEJACKE;
            } else if (temp <= valuejacke - 8) {
                oberteil= Kleidungstyp.PULLOVER;
                jacke = Kleidungstyp.DICKEJACKE;
            }

            //HOSE
            switch (hose) {
                case LANGEHOSE:
                    //Zeige Bild für lange Hose an
                    ivLangeHose.setVisibility(View.VISIBLE);
                    tvHose.setText("Lange Hose");
                    break;
                case KURZEHOSE:
                    //Zeige Bild für kurze Hose an
                    ivkurzeHose.setVisibility(View.VISIBLE);
                    tvHose.setText("Kurze Hose");
                    break;
            }

            //OBERTEIL UND JACKE
            if(oberteil == Kleidungstyp.TSHIRT && jacke == Kleidungstyp.KEINEJACKE){
                ivTshirt.setVisibility(View.VISIBLE);
                tvOberteil.setText("T-Shirt");
                tvJacke.setText("Keine Jacke");
            }else if(oberteil == Kleidungstyp.TSHIRT && jacke == Kleidungstyp.PULLOVER){
                ivTshirt.setVisibility(View.VISIBLE);
                ivPullover.setVisibility(View.VISIBLE);
                tvOberteil.setText("T-Shirt");
                tvJacke.setText("Pullover");
            }else if(oberteil == Kleidungstyp.TSHIRT && jacke == Kleidungstyp.DUENNEJACKE){
                ivTshirt.setVisibility(View.VISIBLE);
                ivDuenneJacke.setVisibility(View.VISIBLE);
                tvOberteil.setText("T-Shirt");
                tvJacke.setText("dünne Jacke");
            }else if(oberteil == Kleidungstyp.PULLOVER && jacke == Kleidungstyp.DUENNEJACKE){
                ivTshirt.setVisibility(View.VISIBLE);
                ivTshirt.setVisibility(View.VISIBLE);
                ivDuenneJacke.setVisibility(View.VISIBLE);
                tvOberteil.setText("Pullover");
                tvJacke.setText("dünne Jacke");
            }else if(oberteil == Kleidungstyp.PULLOVER && jacke == Kleidungstyp.DICKEJACKE){
                ivTshirt.setVisibility(View.VISIBLE);
                ivPullover.setVisibility(View.VISIBLE);
                ivDickeJacke.setVisibility(View.VISIBLE);
                tvOberteil.setText("T-Shirt & Pullover");
                tvJacke.setText("dicke Jacke");
            }

        }
    }


    public static String getDescriptionForCode(int code) {
        switch (code) {
            case 0:
                return "Tornado";
            case 1:
                return "Tropischer Sturm";
            case 2:
                return "Hurricane";
            case 3:
                return "Schweres Gewitter";
            case 4:
                return "Gewitter";
            case 5:
                return "Regen und Schnee";
            case 6:
                return "Regen und Schneeregen";
            case 7:
                return "Schnee und Schneeregen";
            case 8:
                return "Gefrierender Nieselregen";
            case 9:
                return "Nieselregen";
            case 10:
                return "Gefrierender Regen";
            case 11:
                return "Regen";
            case 12:
                return "Regen";
            case 13:
                return "Schneegestöber";
            case 14:
                return "Leichter Schneeregen";
            case 15:
                return "Schneetreiben";
            case 16:
                return "Schnee";
            case 17:
                return "Hagel";
            case 18:
                return "Schneeregen";
            case 19:
                return "Sandsturm";
            case 20:
                return "Nebel";
            case 21:
                return "Nebelschleier";
            case 22:
                return "rauchig";
            case 23:
                return "stürmisch";
            case 24:
                return "windig";
            case 25:
                return "kalt";
            case 26:
                return "bewölkt";
            case 27:
                return "Überwiegend bewölkt";
            case 28:
                return "Überwiegend bewölkt";
            case 29:
                return "Teils bewölkt";
            case 30:
                return "Teils bewölkt";
            case 31:
                return "klar";
            case 32:
                return "sonnig";
            case 33:
                return "Schönwetter";
            case 34:
                return "Schönwetter";
            case 35:
                return "Regen und Hagel";
            case 36:
                return "heiß";
            case 37:
                return "vereinzelte Gewitter";
            case 38:
                return "vereinzelte Gewitter";
            case 39:
                return "vereinzelte Gewitter";
            case 40:
                return "vereinzelte Regenschauer";
            case 41:
                return "Schwerer Schneefall";
            case 42:
                return "aufgelockerter Schneeregen";
            case 43:
                return "Schwerer Schneefall";
            case 44:
                return "Teils bewölkt";
            case 45:
                return "Gewitterregen";
            case 46:
                return "Schneeregen";
            case 47:
                return "vereinzelte Gewitter";
            case 3200:
                return "-"; //nicht verfügbar
        }
        return "";
    }


}


