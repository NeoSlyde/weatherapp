package app.appmeteo.view.center;

import app.appmeteo.model.City;
import app.appmeteo.view.misc.AppLabel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;

import java.awt.*;

public class CenterComponent extends VBox {
    public static AppLabel cityLabel = new AppLabel("", "city-label");
    public static AppLabel cityWatermark = new AppLabel("", "city-watermark");

    private PartOfTheDayComponent morningComponent = new PartOfTheDayComponent("Matin");
    private PartOfTheDayComponent afternoonComponent = new PartOfTheDayComponent("Apres-Midi");

    public PartOfTheDayComponent getMorningComponent() {
        return morningComponent;
    }
    public PartOfTheDayComponent getAfternoonComponent() {
        return afternoonComponent;
    }

    public void setCity(City city) {
        cityLabel.setText(city.toString());
        cityWatermark.setText(city.toString());
    }

    public CenterComponent(Scene scene) {
        this.getChildren().add(new DateSelectorComponent(scene));

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
    }
}
