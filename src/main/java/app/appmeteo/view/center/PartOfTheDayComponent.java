package app.appmeteo.view.center;

import app.appmeteo.view.misc.AppLabel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * This represents a single line in the center component e.g "Matin" + "8°C" +
 * "some icon"
 */
public class PartOfTheDayComponent extends HBox {
    private Label nameLabel;
    private Label temperatureLabel = new AppLabel("", "part-of-the-day-temperature");
    // TODO: Icons

    public void setTemperature(int temperature) {
        temperatureLabel.setText(temperature + "°C");
    }

    public PartOfTheDayComponent(String name) {
        this.nameLabel = new AppLabel(name, "part-of-the-day-name");

        setTemperature(8); // ! Temporary

        VBox vbox = new VBox();
        vbox.setPrefWidth(260);
        this.getChildren().add(vbox);
        vbox.getChildren().add(nameLabel);
        vbox.getChildren().add(temperatureLabel);
        vbox.setPadding(new Insets(25, 0, 0, 0));

        // TODO: Add icon here

        nameLabel.setAlignment(Pos.CENTER);
        temperatureLabel.setAlignment(Pos.CENTER);
        this.setPrefHeight(165);
    }
}
