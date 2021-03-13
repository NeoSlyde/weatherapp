package app.appmeteo.view;

import app.appmeteo.controller.OpenWeatherMapAPI;
import app.appmeteo.model.City;
import app.appmeteo.model.weather.MultiTempWeather;
import app.appmeteo.view.center.CenterComponent;
import app.appmeteo.view.rightBar.RightBarComponnent;
import app.appmeteo.view.topBar.TopBarComponent;
import app.appmeteo.view.leftBar.LeftBarComponent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.LocalDate;
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
        setDate(LocalDate.now());
        setCenterLabels(new City("Marseille"));
        List<MultiTempWeather> weatherList = OpenWeatherMapAPI.singleton.fetchDailyWeather(new City("Marseille"));
        Optional<MultiTempWeather> weather = MultiTempWeather.getWeather(weatherList, LocalDate.now());
        weather.ifPresentOrElse(w -> {
            setWeather(w.morningTemperature.toInt(), w.dayTemperature.toInt());
        }, () -> System.out.println("Méteo introuvable"));

        activate();
    }

    public void setCenterLabels(City city){
        this.centerComponent.setCity(city);
    }

    public String getCity(){
        return this.centerComponent.getCity();
    }

    public void setDate(LocalDate date){
        this.centerComponent.setDate(date);
    }

    public void setWeather(int tempmorning, int tempday){
        this.centerComponent.getMorningComponent().setTemperature(tempmorning);
        this.centerComponent.getAfternoonComponent().setTemperature(tempday);
    }

    public Optional<LocalDate> getCenterDate(){
        return centerComponent.getDate();
    }

    public void activate(){
        this.centerComponent.activate();
    }
}
