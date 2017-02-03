package com.example.htlgrk.whattowear;

import android.app.Application;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

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

        TextView tvOberteil = (TextView) mainView.findViewById(R.id.tvOberteil);
        switch (oberteil) {
            case TSHIRT:
                //Zeige Bild für T-Shirt an
                ivTshirt.setVisibility(View.VISIBLE);
                tvOberteil.setText("T-Shirt");
                break;
            case PULLOVER:
                //Zeige Bild für Pullover an
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

    public String getDescriptionForCode(int code){
        switch (code){
            case 0: return "Tornado";
            case 1: return "Tropischer Sturm";
            case 2: return "Hurricane";
            case 3: return "Schweres Gewitter";
            case 4: return "Gewitter";
            case 5: return "Regen und Schnee";
            case 6: return "Regen und Schneeregen";
            case 7: return "Schnee und Schneeregen";
            case 8: return "Gefrierender Nieselregen";
            case 9: return "Nieselregen";
            case 10: return "Gefrierender Regen";
            case 11: return "Regen";
            case 12: return "Regen";
            case 13: return "Schneegestöber";
            case 14: return "Leichter Schneeregen";
            case 15: return "Schneetreiben";
            case 16: return "Schnee";
            case 17: return "Hagel";
            case 18: return "Schneeregen";
            case 19: return "Sandsturm";
            case 20: return "Nebel";
            case 21: return "Nebelschleier";
            case 22: return "rauchig";
            case 23: return "stürmisch";
            case 24: return "windig";
            case 25: return "kalt";
            case 26: return "bewölkt";
            case 27: return "Überwiegend bewölkt";
            case 28: return "Überwiegend bewölkt";
            case 29: return "Teils bewölkt";
            case 31: return "Teils bewölkt";
            case 32: return "klar";
            case 33: return "sonnig";
            case 34: return "";
            case 35: return "";
            case 36: return "";
            case 37: return "";
            case 38: return "";
            case 39: return "";
            case 40: return "";
            case 41: return "";
            case 42: return "";
            case 43: return "";
            case 44: return "";
            case 45: return "";
            case 46: return "";
            case 47: return "";
            case 3200: return "";
        }
        return "";
    }
}

        /*<code number="0" description="tornado"/>
        <code number="1" description="tropical storm"/>
        <code number="2" description="hurricane"/>
        <code number="3" description="severe thunderstorms"/>
        <code number="4" description="thunderstorms"/>
        <code number="5" description="mixed rain and snow"/>
        <code number="6" description="mixed rain and sleet"/>
        <code number="7" description="mixed snow and sleet"/>
        <code number="8" description="freezing drizzle"/>
        <code number="9" description="drizzle"/>
        <code number="10" description="freezing rain"/>
        <code number="11" description="showers"/>
        <code number="12" description="showers"/>
        <code number="13" description="snow flurries"/>
        <code number="14" description="light snow showers"/>
        <code number="15" description="blowing snow"/>
        <code number="16" description="snow"/>
        <code number="17" description="hail"/>
        <code number="18" description="sleet"/>
        <code number="19" description="dust"/>
        <code number="20" description="foggy"/>
        <code number="21" description="haze"/>
        <code number="22" description="smoky"/>
        <code number="23" description="blustery"/>
        <code number="24" description="windy"/>
        <code number="25" description="cold"/>
        <code number="26" description="cloudy"/>
        <code number="27" description="mostly cloudy (night)"/>
        <code number="28" description="mostly cloudy (day)"/>
        <code number="29" description="partly cloudy (night)"/>
        <code number="30" description="partly cloudy (day)"/>
        <code number="31" description="clear (night)"/>
        <code number="32" description="sunny"/>
        <code number="33" description="fair (night)"/>
        <code number="34" description="fair (day)"/>
        <code number="35" description="mixed rain and hail"/>
        <code number="36" description="hot"/>
        <code number="37" description="isolated thunderstorms"/>
        <code number="38" description="scattered thunderstorms"/>
        <code number="39" description="scattered thunderstorms"/>
        <code number="40" description="scattered showers"/>
        <code number="41" description="heavy snow"/>
        <code number="42" description="scattered snow showers"/>
        <code number="43" description="heavy snow"/>
        <code number="44" description="partly cloudy"/>
        <code number="45" description="thundershowers"/>
        <code number="46" description="snow showers"/>
        <code number="47" description="isolated thundershowers"/>
        <code number="3200" description="not available"/>
        </yahoo-weather-codes>

                function get_de_conditions($input) {
            if($input == „Light Rain“) $data = „Leichter Regen“;
            elseif($input == „Haze“) $data = „Dunst“;
            elseif($input == „Unknown Precipitation“) $data = „Niederschlag“;
            elseif($input == „Partly Cloudy“) $data = „Teilweise bewölkt“;
            elseif($input == „Cloudy“)	$data = „Bewölkt“;
            elseif($input == „Mostly Cloudy“) $data = „überwiegend bewölkt“;
            elseif($input == „Blowing Snow“) $data = „Schneetreiben“;
            elseif($input == „Drizzle“) $data = „Nieselregen“;
            elseif($input == „Light Rain Shower“) $data = „Leichter Regenschauer“;
            elseif($input == „Sunny“) $data = „Sonnig“;
            elseif($input == „Fair“) $data = „Heiter“;
            elseif($input == „Light Drizzle“) $data = „Leichter Nieselregen“;
            elseif($input == „Wintry Mix“) $data = „Winterlicher Mix“;
            elseif($input == „Clear“) $data = „Klar“;
            elseif($input == „Light Snow“) $data = „Leichter Schneefall“;
            elseif($input == „Fog“) $data = „Nebel“;
            elseif($input == „Mist“) $data = „Nebel“;
            elseif($input == „Showers in the Vicinity“) $data = „Regenfälle in der Nähe“;
            elseif($input == „Light Rain/Windy“) $data = „Leichter Regen/Windig“;
            elseif($input == „Rain and Snow“) $data = „Schneeregen“;
            elseif($input == „Light Snow“) $data = „Leichter Schneefall“;
            elseif($input == „Snow“) $data = „Schneefall“;
            elseif($input == „Rain“) $data = „Regen“;
            elseif($input == „Mostly Clear“) $data = „überwiegend Klar“;
            elseif($input == „Foggy“) $data = „neblig“;
            elseif($input == „Freezing Drizzle“) $data = „gefrierender Nieselregen“;
            elseif($input == „Freezing Rain“) $data = „gefrierender Regen“;
            elseif($input == „Mostly sunny“) $data = „überwiegend sonnig“;
            elseif($input == „Light Rain/Freezing Rain“) $data = „Leichter Regen/Gefrierender Regen“;
            elseif($input == „Light Snow Shower“) $data = „Leichter Schneeschauer“;
            elseif($input == „Ice Crystals“) $data = „Eiskristalle“;
            elseif($input == „Thunder“) $data = „Gewitter“;
            elseif($input == „Heavy Thunderstorm“) $data = „Schweres Gewitter“;
            elseif($input == „Thunderstorm“) $data = „Gewitter“;
            elseif($input == „Heavy Rain“) $data = „Starker Regen“;
            elseif($input == „Light Rain with Thunder“) $data = „Leichter Regen mit Gewitter“;
            elseif($input == „Thunder in the Vicinity“) $data = „Gewitter in der Umgebung“;
            elseif($input == „Partly Cloudy/Windy“) $data = „Teilweise bewölkt/windig“;
            elseif($input == „Light Rain Shower/Windy“) $data = „Leichter Regenschauer/windig“;
            elseif($input == „Patches of Fog“) $data = „Nebelfelder“;
            elseif($input == „Rain Shower“) $data = „Regenschauer“;
            elseif($input == „Light Freezing Rain“) $data = „Leichter gefrierender Regen“;
            elseif($input == „Rain Shower/Windy“) $data = „Regenschauer/Windig“;
            elseif($input == „Mostly Cloudy/Windy“) $data = „Meist wolkig/Windig“;
            elseif($input == „Snow Shower“) $data = „Schneeschauer“;
            elseif($input == „Patches of Fog/Windy“) $data = „Nebelfelder/Windig“;
            elseif($input == „Shallow Fog“) $data = „flacher Nebel“;
            elseif($input == „Cloudy/Windy“) $data = „Wolkig/Windig“;
            elseif($input == „Light Snow/Fog“) $data = „Leichter Schneefall/Nebel“;
            elseif($input == „Heavy Rain Shower“) $data = „Starker Regenschauer“;
            elseif($input == „Light Rain Shower/Fog“) $data = „Leichter Regenschauer/Nebel“;
            elseif($input == „Rain/Windy“) $data = „Regen/Windig“;
            elseif($input == „Drizzle/Windy“) $data = „Nieselregen/Windig“;
            elseif($input == „Heavy Drizzle“) $data = „starker Nieselregen“;
            elseif($input == „Squalls“) $data = „Böen“;
            elseif($input == „Heavy Thunderstorm/Windy“) $data = „Schweres Gewitter/Windig“;
            elseif($input == „Snow Grains“) $data = „Schneegriesel“;
            elseif($input == „Partial Fog“) $data = „teilweise Nebel“;
            elseif($input == „Snow/Windy“) $data = „Schnee/Windig“;
            elseif($input == „Fair/Windy“) $data = „Heiter/Windig“;
            elseif($input == „Heavy Snow/Windy“) $data = „Starker Schneefall/Windig“;
            elseif($input == „Heavy Snow“) $data = „Starker Schneefall“;
            elseif($input == „Light Snow Shower/Fog“) $data = „Leichter Schneeschauer/Nebel“;
            elseif($input == „Heavy Snow Shower“) $data = „Starker Schneeschauer“;
            elseif($input == „Light Snow/Windy“) $data = „Leichter Schneeschauer/Windig“;
            elseif($input == „Drifting Snow/Windy“) $data = „Schneetreiben/Windig“;
            elseif($input == „Light Snow/Windy/Fog“) $data = „Leichter Schneeschauer/Windig/Nebel“;
            elseif($input == „Freezing Drizzle/Windy“) $data = „Gefrierender Nieselregen/Windig“;
            elseif($input == „Light Snow/Freezing Rain“) $data = „Leichter Schneefall/Gefrierender Regen“;
            elseif($input == „Light Sleet“) $data = „Leichter Schneeregen“;
            elseif($input == „Light Freezing Drizzle“) $data = „Leichter gefrierender Nieselregen“;
            elseif($input == „Light Snow Grains“) $data = „Leichter Schneegriesel“;
            elseif($input == „Clear/Windy“) $data = „Klar/Windig“;
            elseif($input == „Heavy Rain/Windy“) $data = „Starker Regen/Windig“;
            elseif($input == „Fog/Windy“) $data = „Nebel/Windig“;
            elseif($input == „Unknown“) $data = „unbekannt“;
            elseif($input == „Sunny/Windy“) $data = „Sonnig/Windig“;
            elseif($input == „Sleet and Freezing Rain“) $data = „Schneeregen und gefrierender Regen“;
            elseif($input == „Clear/Windy“) $data = „Klar/Windig“;
            elseif($input == „Thunderstorm/Windy“) $data = „Gewitter/Windig“;
            elseif($input == „Light Snow with Thunder“) $data = „Leichter Schneefall mit Gewitter“;
            elseif($input == „Light Rain/Fog“) $data = „Leichter Regen/Nebel“;
            elseif($input == „Light Snow/Windy/Fog“) $data = „Leichter Schneefall/Windig/Nebel“;
            elseif($input == „Sleet/Windy“) $data = „Schneeregen/Windig“;
            elseif($input == „Rain and Snow/Windy „) $data = „Regen und Schnee/Windig“;
            elseif($input == „Fog/Windy“) $data = „Nebel/Windig“;
            elseif($input == „Rain and Snow/Windy“) $data = „Regen und Schnee/Windig“;
            elseif($input == „Light Freezing Rain/Fog“) $data = „Leichter gefrierender Regen/Nebel“;
            elseif($input == „Drifting Snow“) $data = „Schneetreiben“;
            else $data = $input;
            return $data;*/


