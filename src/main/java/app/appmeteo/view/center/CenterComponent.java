package app.appmeteo.view.center;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class CenterComponent extends VBox {
    public CenterComponent(Scene scene) {
        this.getChildren().add(new DateSelectorComponent(scene));
    }
}
