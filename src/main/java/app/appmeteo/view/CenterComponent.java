package app.appmeteo.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CenterComponent {

    static VBox create() {
        VBox vb = new VBox();
        vb.setSpacing(10);
        Label center = AppGui.createLabel("Meteo de ", "bg-4");
        vb.getChildren().addAll(center);
        vb.setAlignment(Pos.TOP_CENTER);
        return vb;
    }
}
