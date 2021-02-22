package app.appmeteo.view.center;

import app.appmeteo.view.misc.AppLabel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;

public class CenterComponent extends VBox {
    AppLabel cityLabel = new AppLabel("Rennes", "city-label");
    AppLabel cityWatermark = new AppLabel("Rennes", "city-watermark");

    public CenterComponent(Scene scene) {
        this.getChildren().add(new DateSelectorComponent(scene));

        Pane pane = new Pane();
        pane.getChildren().add(cityLabel);
        cityLabel.setPadding(new Insets(25, 0, 0, 50));
        pane.getChildren().add(cityWatermark);
        // Center horizontally
        cityWatermark.layoutXProperty()
                .bind(pane.widthProperty().subtract(cityWatermark.widthProperty()).divide(2).add(50));
        cityWatermark.setPadding(new Insets(-15, 0, 0, 0));
        this.getChildren().add(pane);
    }
}
