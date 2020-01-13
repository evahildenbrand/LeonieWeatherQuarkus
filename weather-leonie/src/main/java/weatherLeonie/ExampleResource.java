package weatherLeonie;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.List;

@Path("/hello")
public class ExampleResource {
    String csvFile = "http://www.zamg.ac.at/ogd/";
    URL url = new URL(csvFile);
    BufferedReader br = null;
    InputStreamReader input = null;
    int lineIndex = 1;
    String line = "";
    String splitLine = ";";
    int town = 1;
    int tempIndex = 5;
    int windSpeedIndex = 9;
    int rainIndex = 12;
    int sunIndex = 15;
    String[] weatherArray;
    String returnString = "";

    public ExampleResource() throws MalformedURLException {
    }

    @GET
    public String actualWeather(){
        JsonArrayBuilder array = Json.createArrayBuilder();
        JsonObjectBuilder object = Json.createObjectBuilder();

        try {
            URLConnection conn = url.openConnection();
            input = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8);

            BufferedReader br = new BufferedReader(input);

            br.readLine();
            String line;
            for(int i = 0; i <= lineIndex-1; i++){
                line = br.readLine();
                weatherArray = line.split(splitLine);

                object.add("Town", weatherArray[town]);
                object.add("Temperatur", weatherArray[tempIndex] + " °C");
                object.add("Windgeschwindigkeit", weatherArray[windSpeedIndex] + " km/h");
                object.add("Regenmenge", weatherArray[rainIndex] + " l/m²");
                object.add("Sonnenschein", weatherArray[sunIndex] + " %");

                returnString = weatherArray[town]+ "\n" +
                        "Temperatur: " + weatherArray[tempIndex] + "°C \n" +
                        "Windgeschwindigkeit: " + weatherArray[windSpeedIndex] + "km/h \n" +
                        "Regenmenge: " + weatherArray[rainIndex] + "l/m² \n" +
                        "Sonnenschein: " + weatherArray[sunIndex] + "%";

                array.add(object);

                System.out.println(object);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException ex){
            System.out.println("Mistake");
        }
        return returnString;
    }
}