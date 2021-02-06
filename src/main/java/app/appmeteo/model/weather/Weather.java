package app.appmeteo.model.weather;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Weather {
    public final LocalDate date;

    final int id;
    final String sky; // e.g "Clouds", "Rain" ...
    final String description; // e.g "light rain", "few clouds" ...
    final String icon;

    public Weather(LocalDate date, int id, String sky, String description, String icon) {
        this.date = date;

        this.id = id;
        this.sky = sky;
        this.description = description;
        this.icon = icon;
    }

    // Copy constructor
    public Weather(Weather weather) {
        this.date = weather.date;
        
        this.id = weather.id;
        this.sky = weather.sky;
        this.description = weather.description;
        this.icon = weather.icon;
    }

    @Override
    public String toString() {
        String dateStr = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String className = getClass().getSimpleName();
        return String.format("%s: %s, %s, %s, %s", 
            className, dateStr, sky, description, icon);
    }
}
