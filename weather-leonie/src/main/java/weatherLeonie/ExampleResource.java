package weatherLeonie;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.List;

@Path("/weather")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExampleResource {
    String csvFile = "http://www.zamg.ac.at/ogd/";
    URL url = new URL(csvFile);
    InputStreamReader input = null;
    int lineIndex = 1;
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
        WeatherModel weather = new WeatherModel();

        try {
            URLConnection conn = url.openConnection();
            input = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8);

            BufferedReader br = new BufferedReader(input);

            br.readLine();
            String line;
            for(int i = 0; i <= lineIndex-1; i++){
                line = br.readLine();
                weatherArray = line.split(splitLine);

                returnString = weatherArray[town]+ "<br> \n" +
                        "Temperatur: " + weatherArray[tempIndex] + "°C <br> \n" +
                        "Windgeschwindigkeit: " + weatherArray[windSpeedIndex] + "km/h <br> \n" +
                        "Regenmenge: " + weatherArray[rainIndex] + "l/m² <br> \n" +
                        "Sonnenschein: " + weatherArray[sunIndex] + "%";

                weather.setCity(weatherArray[town]);
                weather.setTemp(weatherArray[tempIndex]);
                weather.setWindspeed(weatherArray[windSpeedIndex]);
                weather.setRain(weatherArray[rainIndex]);
                weather.setSun(weatherArray[sunIndex]);
                //System.out.println(object);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException ex){
            System.out.println("Mistake");
        }
        return weather.toString();
    }
}