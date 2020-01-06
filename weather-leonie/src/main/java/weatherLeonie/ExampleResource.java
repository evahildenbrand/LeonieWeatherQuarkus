package weatherLeonie;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

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

    public ExampleResource() throws MalformedURLException {
    }

    @GET
    public Response actualWeather(){
        return Response.ok().build();
    }
}