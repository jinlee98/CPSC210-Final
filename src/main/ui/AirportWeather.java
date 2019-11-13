package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


public class AirportWeather {

    public static void checkWeather() throws IOException {

        BufferedReader br = null;

        try {
            String theURL = "http://api.openweathermap.org/data/2.5/weather?q=Vancouver,ca&APPID=909101648ea24d2ff6d2ebeb77ad8fef";
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            System.out.println(sb);
        } finally {

            if (br != null) {
                br.close();
            }
        }
    }
}