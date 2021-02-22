package app.appmeteo.view.center;

import app.appmeteo.controller.InformationController;
import app.appmeteo.view.misc.AppLabel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CenterComponent extends VBox {
    public CenterComponent(Scene scene) {
        this.getChildren().add(new DateSelectorComponent(scene));
        this.getChildren().add(new InformationComponent(scene));

    }
}
