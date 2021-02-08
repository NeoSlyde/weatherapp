package app.appmeteo.model.weather;

public class Temperature {
    private final double temperature;

    public Temperature(int kelvins) {
        this.temperature = kelvins;
    }

    public double toKelvins() {
        return temperature;
    }
    public double toCelcius() {
        return temperature - 273.15;
    }

}