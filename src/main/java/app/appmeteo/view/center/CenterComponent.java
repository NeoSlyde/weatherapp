package app.appmeteo.view.center;

import app.appmeteo.model.City;
import app.appmeteo.view.AppScene;
import app.appmeteo.view.misc.AppLabel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;

import java.time.LocalDate;
import java.util.Optional;

public class CenterComponent extends VBox {
    private AppLabel cityLabel = new AppLabel("", "city-label");
    private AppLabel cityWatermark = new AppLabel("", "city-watermark");
    private DateSelectorComponent dateSelectorComponent;

    private PartOfTheDayComponent morningComponent = new PartOfTheDayComponent("Matin");
    private PartOfTheDayComponent afternoonComponent = new PartOfTheDayComponent("Après-Midi");



    public PartOfTheDayComponent getMorningComponent() {
        return morningComponent;
    }
    public PartOfTheDayComponent getAfternoonComponent() {
        return afternoonComponent;
    }

    public void activate(){
        morningComponent.setVisible(true);
        afternoonComponent.setVisible(true);
        cityWatermark.setVisible(true);
        cityLabel.setVisible(true);
    }

    public void setCity(City city) {
        cityLabel.setText(city.toString());
        cityWatermark.setText(city.toString());
    }

    public String getCity() {
        return cityLabel.getText();
    }

    public void setDate(LocalDate date){
        dateSelectorComponent.setDate(date);
    }

    public Optional<LocalDate> getDate(){
        return dateSelectorComponent.getDate();
    }

    public CenterComponent(Scene scene, AppScene appScene) {
        dateSelectorComponent = new DateSelectorComponent(scene, appScene);
        this.getChildren().add(dateSelectorComponent);

        Pane pane = new Pane();
        this.getChildren().add(pane);

        pane.getChildren().add(cityLabel);
        cityLabel.setPadding(new Insets(25, 0, 0, 50));
        setCity(new City("Rennes")); // ! Temporary
        pane.getChildren().add(cityWatermark);
        cityWatermark.setPadding(new Insets(-15, 0, 0, -90));

        VBox partsOfTheDay = new VBox();
        pane.getChildren().add(partsOfTheDay);
        partsOfTheDay.setPadding(new Insets(0, 0, 0, 220));

        partsOfTheDay.getChildren().add(getMorningComponent());
        partsOfTheDay.getChildren().add(getAfternoonComponent());

        morningComponent.setVisible(false);
        afternoonComponent.setVisible(false);
        cityWatermark.setVisible(false);
        cityLabel.setVisible(false);
    }
}
