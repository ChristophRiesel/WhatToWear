package com.example.htlgrk.whattowear;

import android.app.Application;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Christoph on 13.01.2017.
 */
public class MyApplication extends Application {

    public static void setClothes(int low, int high, View mainView) {
        int mintemp = low;
        int maxtemp = high;

        float x = (maxtemp - mintemp) / 2.5f;
        int val = Math.round(x);
        int value = mintemp + val;

        int temp = value;
        int valuejacke = 15;
        int valuehose = 20;

        Kleidungstyp hose;
        Kleidungstyp jacke;
        Kleidungstyp oberteil;

        ImageView ivMann = (ImageView) mainView.findViewById(R.id.iv_mann);
        ivMann.setVisibility(View.VISIBLE);

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

        if (temp >= valuehose) {
            hose = Kleidungstyp.KURZEHOSE;
        } else {
            hose = Kleidungstyp.LANGEHOSE;
        }

        if (temp <= valuejacke) {
            oberteil = Kleidungstyp.TSHIRT;
            jacke = Kleidungstyp.DUENNEJACKE;

            if (temp <= valuejacke - 4) {
                oberteil = Kleidungstyp.PULLOVER;
                jacke = Kleidungstyp.DUENNEJACKE;

                if (temp <= valuejacke - 8) {
                    jacke = Kleidungstyp.DICKEJACKE;
                }
            }
        } else {
            jacke = Kleidungstyp.KEINEJACKE;
            oberteil = Kleidungstyp.TSHIRT;
        }

        TextView tvOberteil = (TextView) mainView.findViewById(R.id.tvOberteil);
        switch (oberteil) {
            case TSHIRT:
                //Zeige Bild für T-Shirt an
                ivTshirt.setVisibility(View.VISIBLE);
                tvOberteil.setText("T-Shirt");
                break;
            case PULLOVER:
                //Zeige Bild für Pullover an
                ivTshirt.setVisibility(View.VISIBLE);
                ivPullover.setVisibility(View.VISIBLE);
                tvOberteil.setText("Pullover");
                break;
        }

        TextView tvJacke = (TextView) mainView.findViewById(R.id.tvJacke);
        switch (jacke) {
            case DICKEJACKE:
                //Zeige Bild für dicke Jacke an
                ivDickeJacke.setVisibility(View.VISIBLE);
                tvJacke.setText("Dicke Jacke");
                break;
            case DUENNEJACKE:
                //Zeige Bild für dünne Jacke an
                ivDuenneJacke.setVisibility(View.VISIBLE);
                tvJacke.setText("Dünne Jacke");
                break;
            case KEINEJACKE:
                //Zeige Bild für keine Jacke an
                tvJacke.setText("Keine Jacke");
                break;
        }

        TextView tvHose = (TextView) mainView.findViewById(R.id.tvHose);
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
    }

    public String getDescriptionForCode(int code) {
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
                return "Schneegest&oumlber";
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
                return "st&uumlrmisch";
            case 24:
                return "windig";
            case 25:
                return "kalt";
            case 26:
                return "bew&oumllkt";
            case 27:
                return "Überwiegend bew&oumllkt";
            case 28:
                return "Überwiegend bew&oumllkt";
            case 29:
                return "Teils bew&oumllkt";
            case 30:
                return "Teils bew&oumllkt";
            case 31:
                return "klar";
            case 32:
                return "sonnig";
            case 33:
                return "Sch&oumlnwetter";
            case 34:
                return "Sch&oumlnwetter";
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
                return "Teils bew&oumllkt";
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