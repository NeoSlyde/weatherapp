package app.appmeteo.view;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class RightBarComponnent extends HBox {
    public RightBarComponnent() {
        VBox vb = new VBox();
        vb.setSpacing(10);
        Label rightLabel = new AppLabel("", "bg-3");
        rightLabel.setPrefWidth(250);
        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        vb.getChildren().add(rightLabel);
        vb.setBackground(new Background(new BackgroundFill(Color.rgb(255,255,255), CornerRadii.EMPTY, Insets.EMPTY)));

        this.getChildren().add(vb);
        this.getChildren().add(0, separator);
    }

}
