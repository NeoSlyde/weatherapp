package app.appmeteo.model.weather;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherDeserializer {
    public CurrentWeather getCurrentWeather(String json) {
        JSONObject jsonRoot = new JSONObject(json);
        JSONObject jsonWeather = jsonRoot.getJSONArray("weather").getJSONObject(0);
        JSONObject jsonMain = jsonRoot.getJSONObject("main");
        
        LocalDate date = Instant.ofEpochMilli(1000l * jsonRoot.getLong("dt"))
                         .atZone(ZoneId.systemDefault()).toLocalDate();

        int id = jsonWeather.getInt("id");
        String sky = jsonWeather.getString("main");
        String description = jsonWeather.getString("description");
        String icon = jsonWeather.getString("icon");

        Temperature temperature = new Temperature(jsonMain.getDouble("temp"));

        Weather weather = new Weather(date, id, sky, description, icon);
        return new CurrentWeather(weather, temperature);
    }

    public List<MultiTempWeather> getDailyWeather(String json) {
        List<MultiTempWeather> weatherList = new ArrayList<>();

        JSONArray weatherJsonArray = new JSONObject(json).getJSONArray("daily");
        for (Object weatherJsonO : weatherJsonArray) {
            if (!(weatherJsonO instanceof JSONObject))
                continue;
            JSONObject weatherJson = (JSONObject) weatherJsonO;

            // Create Weather object

            LocalDate date = Instant.ofEpochMilli(1000l * weatherJson.getLong("dt"))
                             .atZone(ZoneId.systemDefault()).toLocalDate();
            JSONObject jsonWeatherArray1 = weatherJson.getJSONArray("weather").getJSONObject(0);
            int id = jsonWeatherArray1.getInt("id");
            String sky = jsonWeatherArray1.getString("main");
            String description = jsonWeatherArray1.getString("description");
            String icon = jsonWeatherArray1.getString("icon");

            Weather weather = new Weather(date, id, sky, description, icon);

            // And then create MultiTempWeather object

            JSONObject temperatures = weatherJson.getJSONObject("temp");

            Temperature day = new Temperature(temperatures.getInt("day"));
            Temperature min = new Temperature(temperatures.getInt("min"));
            Temperature max = new Temperature(temperatures.getInt("max"));
            Temperature night = new Temperature(temperatures.getInt("night"));
            Temperature eve = new Temperature(temperatures.getInt("eve"));
            Temperature morn = new Temperature(temperatures.getInt("morn"));

            MultiTempWeather multiTempWeather = new MultiTempWeather(weather, day, min, max, night, eve, morn);
        
            weatherList.add(multiTempWeather);
        }
        return weatherList;
    }
}
