package app.appmeteo.view.center;

import app.appmeteo.model.City;
import app.appmeteo.view.misc.AppLabel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;

public class CenterComponent extends VBox {
    private AppLabel cityLabel = new AppLabel("", "city-label");
    private AppLabel cityWatermark = new AppLabel("", "city-watermark");

    public void setCity(City city) {
        cityLabel.setText(city.toString());
        cityWatermark.setText(city.toString());
    }

    public CenterComponent(Scene scene) {
        this.getChildren().add(new DateSelectorComponent(scene));

        Pane pane = new Pane();
        pane.getChildren().add(cityLabel);
        cityLabel.setPadding(new Insets(25, 0, 0, 50));
        setCity(new City("Rennes")); //! Temporary
        pane.getChildren().add(cityWatermark);
        // Center horizontally
        cityWatermark.layoutXProperty()
                .bind(pane.widthProperty().subtract(cityWatermark.widthProperty()).divide(2).add(50));
        cityWatermark.setPadding(new Insets(-15, 0, 0, 0));
        this.getChildren().add(pane);
    }
}
