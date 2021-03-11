package app.appmeteo.view;

import app.appmeteo.model.City;
import app.appmeteo.view.center.CenterComponent;
import app.appmeteo.view.rightBar.RightBarComponnent;
import app.appmeteo.view.topBar.TopBarComponent;
import app.appmeteo.view.leftBar.LeftBarComponent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.LocalDate;
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
