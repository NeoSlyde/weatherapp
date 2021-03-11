package app.appmeteo.view.rightBar;


import app.appmeteo.controller.OpenWeatherMapAPI;
import app.appmeteo.model.City;
import app.appmeteo.model.weather.MultiTempWeather;

import app.appmeteo.model.City;

import app.appmeteo.view.misc.AppLabel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;



public class RightBarLabel extends VBox {

    private Label cityLabel;
    private Label tempLabel = new AppLabel("","city-label-right-temp");
    // ! PLACEHOLDER
    private ImageView icon = new ImageView("/owm_icons/custom/02d@4x.png");

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

        this.getChildren().add(cityLabel);
        this.setPadding(new Insets(40, 0, 0, 0));

        Pane pane = new Pane();
        pane.setPrefHeight(90);
        this.getChildren().add(pane);
        pane.getChildren().add(tempLabel);
        tempLabel.setLayoutX(145); tempLabel.setLayoutY(25);
        icon.setScaleX(.5); icon.setScaleY(.5);
        icon.setX(-30); icon.setY(-60);
        pane.getChildren().add(icon);
        
    }

    public void setTemperature(int temperature) {
        tempLabel.setText(temperature + "°C");
    }
}
