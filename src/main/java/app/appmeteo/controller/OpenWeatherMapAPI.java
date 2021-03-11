package app.appmeteo.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.json.JSONObject;

import app.appmeteo.model.City;
import app.appmeteo.model.weather.CurrentWeather;
import app.appmeteo.model.weather.MultiTempWeather;
import app.appmeteo.model.weather.WeatherDeserializer;

public class OpenWeatherMapAPI {
    public static final OpenWeatherMapAPI singleton 
        = new OpenWeatherMapAPI("0d2e378a4ce98b9fc40278ffe56e1b76");
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

    public CurrentWeather fetchCurrentWeather(City city) throws IOException {
        String json = fetchURL(new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city.toString() + "&lang=fr&appid=" + apiKey));
        WeatherDeserializer deserializer = new WeatherDeserializer();
        return deserializer.getCurrentWeather(json);
    }

    public boolean fetchCityExists(String city) throws IOException {
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey);
            fetchURL(url);
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    private String fetchURL(URL url) throws IOException {
        try (InputStream in = url.openStream()) {
            return new String(in.readAllBytes());
        }
    }

    private String fetchDailyWeatherJSON(Coordinates coords) throws IOException {
        URL url = new URL("https://api.openweathermap.org/data/2.5/onecall?lat=" + coords.lat + "&lon=" + coords.lon
                + "&exclude=minutely,hourly&lang=fr&appid=" + apiKey);
        return fetchURL(url);
    }

    private Coordinates fetchCoordinates(City city) throws IOException {
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city.toString() + "&appid=" + apiKey);
        String json = fetchURL(url);
        JSONObject jsonCoords = new JSONObject(json).getJSONObject("coord");
        return new Coordinates(jsonCoords.getDouble("lat"), jsonCoords.getDouble("lon"));
    }
}
