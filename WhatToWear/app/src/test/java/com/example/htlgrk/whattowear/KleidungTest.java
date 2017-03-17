package com.example.htlgrk.whattowear;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import junit.framework.TestCase;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by Christoph on 17.03.2017.
 */

public class KleidungTest extends TestCase {

    Preferences pref;

    public void testSetKleidung() throws Exception {
        //Einstellungen
        pref = new Preferences(15, 20, "männlich");
        int mintemp = 25;
        int maxtemp = 30;

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


            //HOSE
            if (temp >= valuehose) {
                hose = Kleidungstyp.KURZEHOSE;
            } else {
                hose = Kleidungstyp.LANGEHOSE;
            }

            //JACKE
            if (temp >= valuejacke + 6) {
                oberteil = Kleidungstyp.TSHIRT;
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
                oberteil = Kleidungstyp.PULLOVER;
                jacke = Kleidungstyp.DICKEJACKE;
            }

            assertEquals(Kleidungstyp.KURZEHOSE, hose);
            assertEquals(Kleidungstyp.KEINEJACKE, jacke);
            assertEquals(Kleidungstyp.TSHIRT, oberteil);

    }



    }
}
