package app.appmeteo.model.weather;

public class Temperature {
    private final int kelvins;

    public Temperature(int kelvins) {
        this.kelvins = kelvins;
    }

    public int toKelvins() {
        return kelvins;
    }
    public int toCelcius() {
        return -1; // TODO
    }
}
