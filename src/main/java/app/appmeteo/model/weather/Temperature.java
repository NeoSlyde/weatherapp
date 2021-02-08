package app.appmeteo.model.weather;

public class Temperature {
    private final double temperature;

    public Temperature(double kelvins) {
        this.temperature = kelvins;
    }

    public double toKelvins() {
        return temperature;
    }
    public double toCelcius() {
        return temperature - 273.15;
    }

    @Override
    public String toString() {
        return toCelcius() + "°C";
    }
}