package weatherLeonie;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.json.JsonValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("2.5")
@RegisterRestClient
public interface OpenWeatherMapService {

    // https://openweathermap.org/api
    // https://itnext.io/how-to-use-query-parameters-with-microprofile-rest-client-16a72be073b9
    // api.openweathermap.org/data/2.5/weather?q={city name}&appid={your api key}
    @GET
    @Path("/weather")
    @Produces(MediaType.APPLICATION_JSON)
    JsonValue getWeatherByCity(
            @QueryParam("q") String city,
            @QueryParam("appid") String key,
            @QueryParam("units") String unit
    );
    
    //api.openweathermap.org/data/2.5/forecast/daily?q={city name}&cnt={cnt}&appid={your api key}
    @GET
    @Path("forecast/daily")
    @Produces(MediaType.APPLICATION_JSON)
    JsonValue getForecastByCity(
            @QueryParam("q") String city,
            @QueryParam("cnt") String days,
            @QueryParam("appid") String key,
            @QueryParam("units") String unit
    );

}
