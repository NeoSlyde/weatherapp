package app.appmeteo.view.topBar;

import app.appmeteo.controller.OpenWeatherMapAPI;
import app.appmeteo.model.City;
import app.appmeteo.model.date.DateTools;
import app.appmeteo.model.weather.MultiTempWeather;
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

public class TopBarComponent extends HBox {

    private HBox middleSection;
    private Label meteoLogo = new AppLabel("Meteo", "title");

    public TopBarComponent(Scene scene) {
        middleSection = new MiddleSection(scene);
        meteoLogo.setPadding(new Insets(0, 0, 0, 12));
        this.getChildren().addAll(meteoLogo, middleSection);
        this.setSpacing(200);
        this.setPadding(new Insets(0, 0, 0, 20));
        this.setBackground(new Background(new BackgroundFill(Color.rgb(55, 55, 55), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setPrefHeight(70);
    }

    private static class MiddleSection extends HBox {
        MiddleSection(Scene scene) {
            this.setSpacing(20);
            this.setAlignment(Pos.CENTER);

            this.getChildren().add(new CityTextField());
            this.getChildren().add(new DateTextField());
            this.getChildren().add(new ArrowButton(scene, () -> {
                TextField city = (TextField) this.getChildren().get(0);
                TextField dateInput = (TextField) this.getChildren().get(1);

                CenterComponent.cityLabel.setText(city.getText());
                CenterComponent.cityWatermark.setText(city.getText());

                Optional<LocalDate> date = Optional.empty();
                date = DateTools.parseDate(dateInput.getText());

                OpenWeatherMapAPI oAPI = new OpenWeatherMapAPI("0d2e378a4ce98b9fc40278ffe56e1b76");
                List<MultiTempWeather> weatherList = oAPI.fetchDailyWeather(new City(city.getText()));

                Optional<MultiTempWeather> weather = MultiTempWeather.getWeather(weatherList, date.get());
                weather.ifPresentOrElse(w -> {
                }, () -> System.out.println("Météo introuvable"));



            }, .65));
        }
    }
}
