package app.appmeteo.view.topBar;

import app.appmeteo.controller.OpenWeatherMapAPI;
import app.appmeteo.model.City;
import app.appmeteo.model.weather.MultiTempWeather;
import app.appmeteo.view.AppScene;
import app.appmeteo.view.misc.*;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TopBarComponent extends HBox{

    public HBox middleSection;
    private Label meteoLogo = new AppLabel("Meteo", "title");
    private double xOffset = 0;
    private double yOffset = 0;

    public TopBarComponent(Scene scene, AppScene appScene) {
        middleSection = new MiddleSection(scene, appScene);
        meteoLogo.setPadding(new Insets(0, 0, 0, 12));
        HBox rightSection = new RightSection(scene,appScene);
        this.getChildren().addAll(meteoLogo, middleSection, rightSection);
        this.setSpacing(276);
        this.setPadding(new Insets(0, 0, 0, 20));
        this.setBackground(new Background(new BackgroundFill(Color.rgb(55, 55, 55), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setPrefHeight(70);

        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        this.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                appScene.getWindow().setX(event.getScreenX() - xOffset);
                appScene.getWindow().setY(event.getScreenY() - yOffset);
            }
        });

    }

    private class MiddleSection extends HBox {
        MiddleSection(Scene scene, AppScene appScene) {
            this.setSpacing(-45);
            this.setAlignment(Pos.CENTER);

            CityTextField cityTextField = new CityTextField();
            this.getChildren().add(cityTextField);

            ArrowButton.Action searchCallback = () -> {
                TextField cityInput = (TextField) this.getChildren().get(0);
                if (cityInput.getText().isEmpty()) return;
                try {
                    if (!OpenWeatherMapAPI.singleton.fetchCityExists(cityInput.getText())) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Chargement de la meteo");
                        alert.setContentText("La ville `" + cityInput.getText() + "` n'existe pas");
                        alert.showAndWait();
                        return;
                    }
                    appScene.setCenterLabels(new City(cityInput.getText()));
            
                    Optional<LocalDate> date = appScene.getCenterDate();
                    if(date.isPresent()){
                        OpenWeatherMapAPI oAPI = OpenWeatherMapAPI.singleton;
                        List<MultiTempWeather> weatherList = oAPI.fetchDailyWeather(new City(cityInput.getText()));
                        Optional<MultiTempWeather> weather = MultiTempWeather.getWeather(weatherList, date.get());
                        weather.ifPresentOrElse(appScene::setWeather, () -> System.out.println("Méteo introuvable"));
                    }
                    appScene.activate();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };
            this.getChildren().add(new ArrowButton(scene, searchCallback, .40));
            cityTextField.setOnKeyReleased(keyEvent -> {
                if (keyEvent.getCode() == KeyCode.ENTER) 
                    searchCallback.run();
            });
        }
    }

    private class RightSection extends HBox {
        RightSection(Scene scene, AppScene appScene) {
            this.getChildren().addAll(new ReduceButton(scene, ()->{
                Stage obj = (Stage) this.getScene().getWindow();
                obj.setIconified(true);
            }, 0.5));

            this.getChildren().addAll(new CloseButton(scene, ()->{
                Platform.exit();
                System.exit(0);
            }, 0.5));

            this.setSpacing(15);
            this.setPadding(new Insets(10, 0, 0, 15));

        }
    }

}
