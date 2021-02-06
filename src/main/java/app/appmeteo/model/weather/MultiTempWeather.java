package app.appmeteo.model.weather;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MultiTempWeather extends Weather {
    public MultiTempWeather(Weather weather, Temperature dayTemp, Temperature minTemp, Temperature maxTemp,
            Temperature nightTemp, Temperature eveningTemp, Temperature morningTemp) {
        super(weather);
        this.dayTemperature = dayTemp;
        this.minTemperature = minTemp;
        this.maxTemperature = maxTemp;
        this.nightTemperature = nightTemp;
        this.eveningTemperature = eveningTemp;
        this.morningTemperature = morningTemp;
    }

    public final Temperature dayTemperature;
    public final Temperature minTemperature;
    public final Temperature maxTemperature;
    public final Temperature nightTemperature;
    public final Temperature eveningTemperature;
    public final Temperature morningTemperature;

    public static Optional<MultiTempWeather> getWeather(List<MultiTempWeather> weatherList, LocalDate date) {
        Optional<MultiTempWeather> result = weatherList.stream()
            .filter(w -> w.date.getYear()      == date.getYear()
                      && w.date.getDayOfYear() == date.getDayOfYear())
            .findAny();
        return result;
    }
}
