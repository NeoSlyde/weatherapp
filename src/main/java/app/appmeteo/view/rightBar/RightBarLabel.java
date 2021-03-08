package app.appmeteo.view.rightBar;

import app.appmeteo.model.City;
import app.appmeteo.view.misc.AppLabel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class RightBarLabel extends VBox {

    private Label cityLabel;
    private City city;
    private Label temperatureLabel = new AppLabel("","part-of-the-day-temperature");

    public RightBarLabel(City city){
        this.cityLabel = new AppLabel(city.toString(), "city-label");
        this.city = city;

        setTemperature(8);
        cityLabel.setAlignment(Pos.CENTER);
        temperatureLabel.setPadding(new Insets(20, 0, 10, 150));

        this.getChildren().add(cityLabel);
        this.getChildren().add(temperatureLabel);
    }

    public void setTemperature(int temperature) {
        temperatureLabel.setText(temperature + "°C");
    }
}
