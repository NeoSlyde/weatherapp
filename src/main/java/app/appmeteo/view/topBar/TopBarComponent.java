package app.appmeteo.view.topBar;

import app.appmeteo.controller.OpenWeatherMapAPI;
import app.appmeteo.model.City;
import app.appmeteo.model.date.DateTools;
import app.appmeteo.model.weather.MultiTempWeather;
import app.appmeteo.view.AppScene;
import app.appmeteo.view.center.CenterComponent;
import app.appmeteo.view.center.PartOfTheDayComponent;
import app.appmeteo.view.misc.*;
import javafx.geometry.*;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TopBarComponent extends HBox{

    public HBox middleSection;
    private Label meteoLogo = new AppLabel("Meteo", "title");

    public TopBarComponent(Scene scene, AppScene appScene) {
        middleSection = new MiddleSection(scene, appScene);
        meteoLogo.setPadding(new Insets(0, 0, 0, 12));
        this.getChildren().addAll(meteoLogo, middleSection);
        this.setSpacing(276);
        this.setPadding(new Insets(0, 0, 0, 20));
        this.setBackground(new Background(new BackgroundFill(Color.rgb(55, 55, 55), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setPrefHeight(70);
    }

    private class MiddleSection extends HBox {
        MiddleSection(Scene scene, AppScene appScene) {
            this.setSpacing(-45);
            this.setAlignment(Pos.CENTER);

            this.getChildren().add(new CityTextField());
            //this.getChildren().add(new DateTextField());

            this.getChildren().add(new ArrowButton(scene, () -> {
                TextField cityInput = (TextField) this.getChildren().get(0);
                //TextField dateInput = (TextField) this.getChildren().get(1);
                appScene.setCenterLabels(new City(cityInput.getText()));

                //Optional<LocalDate> date = Optional.empty();
                Optional<LocalDate> date = appScene.getCenterDate();
                if(date.isPresent()){
                    OpenWeatherMapAPI oAPI = new OpenWeatherMapAPI("0d2e378a4ce98b9fc40278ffe56e1b76");
                    List<MultiTempWeather> weatherList = oAPI.fetchDailyWeather(new City(cityInput.getText()));
                    Optional<MultiTempWeather> weather = MultiTempWeather.getWeather(weatherList, date.get());
                    weather.ifPresentOrElse(w -> {
                        appScene.setWeather(w.morningTemperature.toInt(), w.dayTemperature.toInt());
                    }, () -> System.out.println("M�t�o introuvable"));
                }
                appScene.activate();
            }, .40));

        }
    }
}
