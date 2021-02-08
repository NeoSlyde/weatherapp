package app.appmeteo.model.weather;

public class CurrentWeather extends Weather {
    public CurrentWeather(Weather weather, Temperature temperature) {
        super(weather);
        this.temperature = temperature;
    }
    public final Temperature temperature;

    @Override
    public String toString() {
        return super.toString() + ", " + temperature;
    }
}
