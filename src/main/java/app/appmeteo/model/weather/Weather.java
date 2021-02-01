package app.appmeteo.model.weather;

import java.util.Date;
import java.util.List;

import app.appmeteo.model.City;

public class Weather {
    public final City city;
    public final Date date;
    public final List<Situation> situations;

    public Weather(City city, Date date, List<Situation> situations) {
        this.city = city;
        this.date = date;
        this.situations = situations;
    }

    public static class Situation {
        int id;
        String main; // e.g "rain", "mist", ...
        String description;
    }
}
