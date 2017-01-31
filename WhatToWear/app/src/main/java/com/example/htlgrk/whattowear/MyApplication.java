package com.example.htlgrk.whattowear;

import android.app.Application;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

/**
 * Created by Christoph on 13.01.2017.
 */
public class MyApplication extends Application {

    public static void setClothes(int low, int high, View mainView){

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

        RadioButton rbOberteil = (RadioButton) mainView.findViewById(R.id.rbOberteil);
        switch (oberteil) {
            case TSHIRT:
                //Zeige Bild für T-Shirt an
                ivTshirt.setVisibility(View.VISIBLE);
                rbOberteil.setText("T-Shirt");
                break;
            case PULLOVER:
                //Zeige Bild für Pullover an
                ivPullover.setVisibility(View.VISIBLE);
                rbOberteil.setText("Pullover");
                break;
        }

        RadioButton rbJacke = (RadioButton) mainView.findViewById(R.id.rbJacke);
        switch (jacke) {
            case DICKEJACKE:
                //Zeige Bild für dicke Jacke an
                ivDickeJacke.setVisibility(View.VISIBLE);
                rbJacke.setText("Dicke Jacke");
                break;
            case DUENNEJACKE:
                //Zeige Bild für dünne Jacke an
                ivDuenneJacke.setVisibility(View.VISIBLE);
                rbJacke.setText("Dünne Jacke");
                break;
            case KEINEJACKE:
                //Zeige Bild für keine Jacke an
                rbJacke.setText("Keine Jacke");
                break;
        }

        RadioButton rbHose = (RadioButton) mainView.findViewById(R.id.rbHose);
        switch (hose) {
            case LANGEHOSE:
                //Zeige Bild für lange Hose an
                ivLangeHose.setVisibility(View.VISIBLE);
                rbHose.setText("Lange Hose");
                break;
            case KURZEHOSE:
                //Zeige Bild für kurze Hose an
                ivkurzeHose.setVisibility(View.VISIBLE);
                rbHose.setText("Kurze Hose");
                break;

        }


    }
}
