package app.appmeteo.model.weather;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class WeatherDeserializerTest {
    @Test
    public void dailyWeatherTest1() throws IOException {
        WeatherDeserializer deserializer = new WeatherDeserializer();
        File jsonFile = new File("./src/test/resources/daily_weather_test.json");
        assertTrue(jsonFile.exists(), "Test files are absent");
        String fileContent = Files.readString(jsonFile.toPath());

        List<MultiTempWeather> weatherList = deserializer.getDailyWeather(fileContent);
        
        Optional<MultiTempWeather> weather_06_02_2021 = MultiTempWeather.getWeather(weatherList, LocalDate.of(2021, 2, 6));
        assertTrue(weather_06_02_2021.isPresent());
        assertEquals("Rain", weather_06_02_2021.get().sky);
        assertEquals("light rain", weather_06_02_2021.get().description);
        
        Optional<MultiTempWeather> weather_08_02_2021 = MultiTempWeather.getWeather(weatherList, LocalDate.of(2021, 2, 8));
        assertTrue(weather_08_02_2021.isPresent());
        assertEquals("Clouds", weather_08_02_2021.get().sky);
        assertEquals("broken clouds", weather_08_02_2021.get().description);
        
        Optional<MultiTempWeather> weather_11_02_2021 = MultiTempWeather.getWeather(weatherList, LocalDate.of(2021, 2, 11));
        assertTrue(weather_11_02_2021.isPresent());
        assertEquals("Snow", weather_11_02_2021.get().sky);
        assertEquals("rain and snow", weather_11_02_2021.get().description);

        Optional<MultiTempWeather> weather_14_02_2021 = MultiTempWeather.getWeather(weatherList, LocalDate.of(2021, 2, 14));
        assertFalse(weather_14_02_2021.isPresent());
    }
}
