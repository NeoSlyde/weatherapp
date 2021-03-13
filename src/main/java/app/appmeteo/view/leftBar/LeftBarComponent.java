package app.appmeteo.view.leftBar;

import app.appmeteo.controller.OpenWeatherMapAPI;
import app.appmeteo.model.*;
import app.appmeteo.model.date.DateTools;
import app.appmeteo.model.weather.MultiTempWeather;
import app.appmeteo.view.AppScene;
import app.appmeteo.view.center.CenterComponent;
import app.appmeteo.view.misc.AppLabel;
import app.appmeteo.view.rightBar.RightBarComponnent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class LeftBarComponent extends VBox {

    protected static ListView<Label> listView = new ListView<>();
    protected static Favorites favorites;

    static {
        try {
            favorites = Favorites.readFavoriteFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LeftBarComponent(Scene scene, AppScene appScene) throws IOException {

        //Button

        Button buttonAddCity = new Button("Ajouter la ville actuelle");
        buttonAddCity.setMaxWidth(240);
        buttonAddCity.getStyleClass().add("addCityCurrent");
        buttonAddCity.setPadding(new Insets(10,0,0,0));

        // ListView
        listView.setMaxWidth(240);
        listView.setPrefHeight(430);

        for(City city : favorites.getList()){
            Label cityLabel = new AppLabel(city.toString(), "favorites-item-label");
            listView.getItems().add(cityLabel);
            cityLabel.setPadding(new Insets(0, 0, 15, 25));
            RightBarComponnent.addLabel(city);
        }

        buttonAddCity.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                City city = new City(appScene.getCity());
                try {
                    if (!OpenWeatherMapAPI.singleton.fetchCityExists(city.toString())) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Erreur");
                        alert.setHeaderText("Chargement de la meteo");
                        alert.setContentText("La ville `" + city + "` n'existe pas");
                        alert.showAndWait();

                        return;
                    }
                    boolean success = false;
                    for (City city1 : favorites.getList()) {
                        if (city.equals(city1)) {
                            success = false;
                            break;
                        }
                        success = true;
                    }
                    if (favorites.getList().isEmpty() || success) {
                        favorites.add(city);
                        Label cityLabel = new AppLabel(city.toString(), "favorites-item-label");
                        listView.getItems().add(cityLabel);
                        cityLabel.setPadding(new Insets(0, 0, 15, 25));

                        try {
                            favorites.writeFavorite2File();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        RightBarComponnent.addLabel(city);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        listView.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.DELETE && listView.getItems().size() != 0) {
                int selectedIndices = listView.getSelectionModel().getSelectedIndex();
                Label selectedCityLabel = listView.getSelectionModel().getSelectedItem();
                favorites.get(selectedCityLabel.getText()).ifPresent(c -> favorites.remove(c));
                listView.getItems().remove(selectedIndices);

                try {
                    favorites.writeFavorite2File();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                RightBarComponnent.remove(selectedIndices);
            }
        });

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!listView.getItems().isEmpty()) {
                    Label selectedCityLabel = listView.getSelectionModel().getSelectedItem();
                    if(selectedCityLabel == null){
                        return;
                    }
                    appScene.setCenterLabels(new City(selectedCityLabel.getText()));

                    //Optional<LocalDate> date = Optional.empty();
                    Optional<LocalDate> date = appScene.getCenterDate();
                    if(date.isPresent()){
                        //appScene.setDate(date.get());
                        OpenWeatherMapAPI oAPI = OpenWeatherMapAPI.singleton;
                        List<MultiTempWeather> weatherList = oAPI.fetchDailyWeather(new City(selectedCityLabel.getText()));
                        Optional<MultiTempWeather> weather = MultiTempWeather.getWeather(weatherList, date.get());
                        weather.ifPresentOrElse(w -> {
                            appScene.setWeather(w.morningTemperature.toInt(), w.dayTemperature.toInt());
                        }, () -> System.out.println("Méteo introuvable"));
                    }
                    appScene.activate();
                }
            }
        });

        this.getChildren().addAll(new FavoriteLabel(appScene), listView,buttonAddCity/*, textField*/);
        this.setSpacing(25);

        this.setBackground(
                new Background(new BackgroundFill(Color.rgb(221, 221, 221), CornerRadii.EMPTY, Insets.EMPTY)));
    }

}
