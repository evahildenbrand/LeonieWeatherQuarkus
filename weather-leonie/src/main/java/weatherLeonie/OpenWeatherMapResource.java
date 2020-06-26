package weatherLeonie;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.json.*;
import javax.print.attribute.standard.Media;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("openweather")
public class OpenWeatherMapResource {

    @Inject
    @RestClient
    OpenWeatherMapService openWeatherMapService;

    @ConfigProperty(name = "openweathermap.api-key")
    String apiKey;

    String tempUnit = "metric";

    String website = "http://localhost:8080";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWeather(@QueryParam("city") String city) {
//        JsonObject weatherByCity = Json.createObjectBuilder()
//                .add("city", city)
//                .add("api-key", apiKey)
//                .build();
        //JsonValue weatherByCity = this.openWeatherMapService.getWeatherByCity(city);
        JsonValue weatherByCity = this.openWeatherMapService.getWeatherByCity(city,apiKey,tempUnit);

        return Response
                .ok(weatherByCity)
                .build();
    }

    @GET
    @Path("/forecast")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getForecast(@QueryParam("city") String city, @QueryParam("days") String days){
        JsonValue forecastByCity = this.openWeatherMapService.getForecastByCity(city,days,apiKey,tempUnit);

        return Response
                .ok(forecastByCity)
                .build();
    }

    @GET
    @Path("/leonieWeather")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJsonForLeonie(@QueryParam("city") String city){
        JsonValue weatherByCity = this.openWeatherMapService.getWeatherByCity(city,apiKey,tempUnit);

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

    @GET
    @Path("apikey")
    public String getKey() {
//        JsonObject weatherByCity = Json.createObjectBuilder()
//                .add("city", city)
//                .add("api-key", apiKey)
//                .build();
        return apiKey;
    }

}
