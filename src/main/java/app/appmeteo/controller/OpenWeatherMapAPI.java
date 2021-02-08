package app.appmeteo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.json.JSONObject;

import app.appmeteo.model.City;
import app.appmeteo.model.weather.MultiTempWeather;
import app.appmeteo.model.weather.WeatherDeserializer;

public class OpenWeatherMapAPI {
    private String apiKey;

    public OpenWeatherMapAPI(String apiKey) {
        this.apiKey = apiKey;
    }
    public static class Coordinates {
        public final double lat, lon;

        public Coordinates(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }
        @Override public String toString() { 
            return String.format("{ lat: %f, lon: %f }", lat, lon);
        }
    }

    // Returns null if fetch failed
    public List<MultiTempWeather> fetchDailyWeather(City city) {
        try {
            String json = fetchDailyWeatherJSON(fetchCoordinates(city));
            WeatherDeserializer deserializer = new WeatherDeserializer();
            return deserializer.getDailyWeather(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String fetchURL(URL url) throws IOException {
        try (InputStream in = url.openStream()) {
            return new String(in.readAllBytes());
        }
    }

    private String fetchDailyWeatherJSON(Coordinates coords) throws IOException {
        URL url = new URL("https://api.openweathermap.org/data/2.5/onecall?lat=" + coords.lat + "&lon=" + coords.lon
                + "&exclude=minutely,hourly&appid=" + apiKey);
        return fetchURL(url);
    }

    private Coordinates fetchCoordinates(City city) throws IOException {
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city.toString() + "&appid=" + apiKey);
        String json = fetchURL(url);
        JSONObject jsonCoords = new JSONObject(json).getJSONObject("coord");
        return new Coordinates(jsonCoords.getDouble("lat"), jsonCoords.getDouble("lon"));
    }
}