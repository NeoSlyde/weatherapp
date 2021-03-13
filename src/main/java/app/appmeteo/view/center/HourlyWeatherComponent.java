package app.appmeteo.view.center;

import app.appmeteo.view.misc.AppLabel;
import app.appmeteo.view.misc.ArrowButton;

import java.time.LocalDateTime;
import java.util.List;

import app.appmeteo.model.weather.Weather;
import app.appmeteo.view.AppScene;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class HourlyWeatherComponent extends Region {
    public void setWeather(List<Weather> list, LocalDateTime time) {
        this.weatherData = list;
        this.time = time;
    }
    public void update() {
        if (weatherData == null) return;
        for (int i = 0; i < 5; ++i) {
            Label label = (Label) hoursHBox.getChildren().get(i);
            label.setText(time.plusHours(i).getHour() + "h");
        }
        int i = 0;
        for (Weather w : weatherData) {
            if (i >= 5) break;
            if (w.date.compareTo(time) < 0) continue;
            ImageView icon = (ImageView) ((Group) iconsHBox.getChildren().get(i)).getChildren().get(0);
            icon.setImage(new Image("/owm_icons/custom/" + w.icon + "@4x.png"));
            ++i;
        }
    }

    private List<Weather> weatherData = null;
    private LocalDateTime time = LocalDateTime.now();

    private HBox iconsHBox = new HBox();
    private HBox hoursHBox = new HBox();
    private ArrowButton.Action leftButtonCallback = () -> {
        if (weatherData == null) return;
        LocalDateTime earliestTimePossible = weatherData.get(0).date;
        if (time.minusHours(1).compareTo(earliestTimePossible) < 0)
            return;
        time = time.minusHours(1);
        update();
    };
    private ArrowButton.Action rightButtonCallback = () -> {
        if (weatherData == null) return;
        LocalDateTime latestTimePossible = weatherData.get(weatherData.size() - 1).date;
        if (time.plusHours(6).compareTo(latestTimePossible) > 0)
            return;
        time = time.plusHours(1);
        update();
    };
    public HourlyWeatherComponent(AppScene scene) {
        Region rect = new Region();
        rect.setPrefSize(520, 70);
        rect.setStyle("-fx-background-color: #ffffff00;" +
                      "-fx-border-width: 3;"             +
                      "-fx-border-color: #67b7ff;"       +
                      "-fx-border-radius: 100px;"
                     );
        this.getChildren().add(new Group(rect));

        iconsHBox.setPadding(new Insets(-3, 0, 0, 70));
        for (int i = 0; i < 5; ++i) {
            ImageView img = new ImageView("/owm_icons/custom/02d@4x.png");
            img.setScaleX(.38);
            img.setScaleY(.38);
            iconsHBox.getChildren().add(new Group(img));
        }
        this.getChildren().add(iconsHBox);

        hoursHBox.setPadding(new Insets(70, 0, 0, 95));
        for (int i = 0; i < 5; ++i) {
            Label label = new AppLabel("??", "hourly-weather-label");
            label.setMinWidth(76);
            hoursHBox.getChildren().add(label);
        }
        this.getChildren().add(hoursHBox);

        ArrowButton leftButton = new ArrowButton(scene, leftButtonCallback, -.6);
        leftButton.setTranslateX(5);
        leftButton.setPadding(new Insets(10, 10, 10, 20));
        this.getChildren().add(leftButton);

        ArrowButton rightButton = new ArrowButton(scene, rightButtonCallback, .6);
        rightButton.setPadding(new Insets(10, 10, 10, 20));
        rightButton.setTranslateX(435);
        this.getChildren().add(rightButton);
    }
}
