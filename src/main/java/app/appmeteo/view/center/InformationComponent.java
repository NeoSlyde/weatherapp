package app.appmeteo.view.center;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import app.appmeteo.controller.InformationController;
import app.appmeteo.model.date.DateTools;
import app.appmeteo.view.misc.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class InformationComponent extends HBox {
    public static InformationController information = new InformationController();
    public static Label centerLabel = new AppLabel("", "day");

    public InformationComponent(Scene scene) {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(15);
        this.setPadding(new Insets(40, 0, 0, 0));


        centerLabel.setAlignment(Pos.CENTER);
        centerLabel.setPrefWidth(250);

        this.getChildren().addAll(centerLabel);

    }

    public void setCity(){

    }
}
