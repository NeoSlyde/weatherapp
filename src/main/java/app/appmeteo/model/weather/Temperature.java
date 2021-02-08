package app.appmeteo.model.weather;

public class Temperature {
    private final double temperature;
    private boolean isKelvins;

    public Temperature(int kelvins) {
        this.temperature = kelvins;
        this.isKelvins = true;
    }

    public double toKelvins() {
        if(isKelvins){
            return temperature;
        }
        return temperature - 273.15;
    }
    public double toCelcius() {
        if(!isKelvins){
            return temperature + 273.15;
        }
        return temperature;
    }

}