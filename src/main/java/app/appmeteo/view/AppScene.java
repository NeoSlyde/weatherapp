package app.appmeteo.view;

import app.appmeteo.controller.OpenWeatherMapAPI;
import app.appmeteo.model.City;
import app.appmeteo.model.weather.MultiTempWeather;
import app.appmeteo.model.weather.Weather;
import app.appmeteo.view.center.CenterComponent;
import app.appmeteo.view.rightBar.RightBarComponnent;
import app.appmeteo.view.topBar.TopBarComponent;
import app.appmeteo.view.leftBar.LeftBarComponent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class AppScene extends Scene {
    CenterComponent centerComponent = new CenterComponent(this, this);
    TopBarComponent topBarComponent = new TopBarComponent(this, this);
    LeftBarComponent leftBarComponent = new LeftBarComponent(this, this);
    RightBarComponnent rightBarComponent = new RightBarComponnent(this);

    public AppScene() throws IOException {
        super(new BorderPane(), 1200, 700);
        BorderPane layout = (BorderPane) this.getRoot();
        layout.getStyleClass().add("bg-1");
        layout.setCenter(centerComponent);
        layout.setTop(topBarComponent);
        layout.setLeft(leftBarComponent);
        layout.setRight(rightBarComponent);

        // Default values
        setWeather(new City("Marseille"), LocalDate.now());
        activate();
    }

    public String getCity(){
        return this.centerComponent.getCity();
    }

    public void setWeather(City city, LocalDate date){
        List<MultiTempWeather> weatherList = OpenWeatherMapAPI.singleton.fetchDailyWeather(city);
        Optional<MultiTempWeather> weather = MultiTempWeather.getWeather(weatherList, date);
        weather.ifPresentOrElse(w -> {
            this.centerComponent.setDate(date);
            this.centerComponent.setCity(city);

            this.centerComponent.getMorningComponent().setTemperature((int) w.morningTemperature.toCelcius());
            this.centerComponent.getMorningComponent().setIcon(w.icon);
            this.centerComponent.getAfternoonComponent().setTemperature((int) w.dayTemperature.toCelcius());
            this.centerComponent.getAfternoonComponent().setIcon(w.icon);
        }, () -> System.out.println("Méteo introuvable"));

        List<Weather> hourly = OpenWeatherMapAPI.singleton.fetchHourlyWeather(city);
        LocalDateTime hourlyDate = LocalDateTime.of(date, LocalTime.now());
        this.centerComponent.getHourlyWeatherComponent().setWeather(hourly, hourlyDate);
        this.centerComponent.getHourlyWeatherComponent().update();

        // With the above approach we always have the same icon twice
        // So we try to fetch the icons from the hourly OWM API

        final int MORNING_HOUR   = 10,
                  AFTERNOON_HOUR = 17;
 
        hourly.stream()
            .filter(w -> w.date.getYear()    == date.getYear()
                    && w.date.getDayOfYear() == date.getDayOfYear()
                    && w.date.getHour()      == MORNING_HOUR)
            .findAny().map(w -> w.icon)
            .ifPresent(this.centerComponent.getMorningComponent()::setIcon);
        hourly.stream()
            .filter(w -> w.date.getYear()    == date.getYear()
                    && w.date.getDayOfYear() == date.getDayOfYear()
                    && w.date.getHour()      == AFTERNOON_HOUR)
            .findAny().map(w -> w.icon)
            .ifPresent(this.centerComponent.getAfternoonComponent()::setIcon);
    }

    public Optional<LocalDate> getCenterDate(){
        return centerComponent.getDate();
    }

    public void activate(){
        this.centerComponent.activate();
    }
}
