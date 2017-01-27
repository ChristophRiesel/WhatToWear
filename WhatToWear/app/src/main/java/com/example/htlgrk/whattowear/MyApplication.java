package com.example.htlgrk.whattowear;

import android.app.Activity;
import android.app.Application;

/**
 * Created by Christoph on 13.01.2017.
 */
public class MyApplication extends Application {

    public void setClothes(int low, int high, Activity mainActivity){

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



        if(temp >= valuehose){
            hose = Kleidungstyp.KURZEHOSE;
        }else{
            hose = Kleidungstyp.LANGEHOSE;
        }

        if(temp <= valuejacke){

            oberteil = Kleidungstyp.TSHIRT;
            jacke = Kleidungstyp.DUENNEJACKE;

            if(temp <= valuejacke-4){
                oberteil = Kleidungstyp.PULLOVER;
                jacke = Kleidungstyp.DUENNEJACKE;

                if(temp <= valuejacke - 8){
                    jacke = Kleidungstyp.DICKEJACKE;
                }
            }
        }else{
            jacke = Kleidungstyp.KEINEJACKE;
            oberteil = Kleidungstyp.TSHIRT;
        }


        switch (oberteil) {
            case TSHIRT:
                //Zeige Bild für T-Shirt an


                break;
            case PULLOVER:
                //Zeige Bild für Pullover an

                break;
        }

        switch (jacke) {
            case DICKEJACKE:
                //Zeige Bild für dicke Jacke an


                break;
            case DUENNEJACKE:
                //Zeige Bild für dünne Jacke an

                break;
            case KEINEJACKE:
                //Zeige Bild für keine Jacke an
                break;
        }

        switch (hose) {
            case LANGEHOSE:
                //Zeige Bild für lange Hose an


                break;
            case KURZEHOSE:
                //Zeige Bild für kurze Hose an

                break;

        }
    }
}
