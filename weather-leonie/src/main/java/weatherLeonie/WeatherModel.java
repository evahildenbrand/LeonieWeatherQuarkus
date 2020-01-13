package weatherLeonie;

public class WeatherModel {
    private String city;
    private String temp;
    private String windspeed;
    private String rain;
    private String sun;

    public WeatherModel() {
    }

    public WeatherModel(String city, String temp, String windspeed, String rain, String sun) {
        this.city = city;
        this.temp = temp;
        this.windspeed = windspeed;
        this.rain = rain;
        this.sun = sun;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getSun() {
        return sun;
    }

    public void setSun(String sun) {
        this.sun = sun;
    }
}
