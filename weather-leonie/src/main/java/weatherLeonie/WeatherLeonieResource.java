package weatherLeonie;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("debug-token")
public class WeatherLeonieResource {

    @Inject
    OpenWeatherMapResource openWeatherMapResource;

    String website = "http://localhost:8080";

    @GET
    @Path("/weather")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJsonForLeonie(@QueryParam("city") String city){
        //JsonValue weatherByCity = this.openWeatherMapService.getWeatherByCity(city,apiKey,tempUnit);
        Response weatherResponse = openWeatherMapResource.getWeather(city);
        JsonValue weatherByCity = (JsonValue) weatherResponse.getEntity();

        JsonObject response = weatherByCity.asJsonObject();
        JsonArray weather = response.getJsonArray("weather");
        JsonObject weatherObject = weather.getJsonObject(0);
        String icon = "http://openweathermap.org/img/w/" + weatherObject.getString("icon") + ".png";

        JsonObject weatherJson = Json.createObjectBuilder()
                .add("Response", response)
                .add("Icon", icon)
                .add("Website", website)
                .build();

        return Response.ok(weatherJson).build();
    }
}
