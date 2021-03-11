package app.appmeteo.view.rightBar;


import app.appmeteo.controller.OpenWeatherMapAPI;
import app.appmeteo.model.City;
import app.appmeteo.model.weather.MultiTempWeather;

import app.appmeteo.model.City;

import app.appmeteo.view.misc.AppLabel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;



public class RightBarLabel extends VBox {

    private Label cityLabel;
    private Label temperatureLabel = new AppLabel("","city-label-right-temp");

    public RightBarLabel(City city){
        this.cityLabel = new AppLabel(city.toString(), "city-label-right");

        LocalDate currentDate = LocalDate.now();
        OpenWeatherMapAPI oAPI = OpenWeatherMapAPI.singleton;
        List<MultiTempWeather> weatherList = oAPI.fetchDailyWeather(city);

        Optional<MultiTempWeather> weather = MultiTempWeather.getWeather(weatherList, currentDate);
        weather.ifPresentOrElse(w -> {
            setTemperature(w.dayTemperature.toInt());
        }, () -> System.out.println("Météo introuvable"));

        cityLabel.setAlignment(Pos.CENTER);
        temperatureLabel.setPadding(new Insets(20, 0, 10, 150));
        this.setPadding(new Insets(30, 0, 0, 0));

        this.getChildren().add(cityLabel);
        this.getChildren().add(temperatureLabel);
    }

    public void setTemperature(int temperature) {
        temperatureLabel.setText(temperature + "°C");
    }
}
