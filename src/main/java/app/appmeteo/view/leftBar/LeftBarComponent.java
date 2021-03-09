package app.appmeteo.view.leftBar;

import app.appmeteo.controller.OpenWeatherMapAPI;
import app.appmeteo.model.*;
import app.appmeteo.model.date.DateTools;
import app.appmeteo.model.weather.MultiTempWeather;
import app.appmeteo.view.AppScene;
import app.appmeteo.view.center.CenterComponent;
import app.appmeteo.view.misc.AppLabel;
import app.appmeteo.view.rightBar.RightBarComponnent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class LeftBarComponent extends VBox {
    public LeftBarComponent(Scene scene, AppScene appScene) throws IOException {


        Favorites favorites = Favorites.readFavoriteFromFile();

        Label leftLabel = new AppLabel("Favoris", "bg-5");
        leftLabel.setPadding(new Insets(20,20,20,34));

        // TextField
        TextField textField = new TextField();
        textField.setPromptText("Ajouter un favori");
        textField.setMaxWidth(178);
        VBox.setMargin(textField, new Insets(0, 0, 0, 30));

        textField.getStyleClass().add("addCity");

        // ListView
        ListView<Label> listView = new ListView<>();
        listView.setMaxWidth(240);
        listView.setPrefHeight(430);

        for(City city : favorites.getList()){
            Label cityLabel = new AppLabel(city.toString(), "favorites-item-label");
            listView.getItems().add(cityLabel);
            cityLabel.setPadding(new Insets(0, 0, 15, 25));
            RightBarComponnent.addLabel(city);
        }


        textField.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                City city = new City(textField.getText());
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
                    textField.setText("");

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
            }
        });

        listView.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.DELETE) {
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
                    appScene.setCenterLabels(new City(selectedCityLabel.getText()));

                    //Optional<LocalDate> date = Optional.empty();
                    Optional<LocalDate> date = appScene.getCenterDate();
                    if(date.isPresent()){
                        //appScene.setDate(date.get());
                        OpenWeatherMapAPI oAPI = new OpenWeatherMapAPI("0d2e378a4ce98b9fc40278ffe56e1b76");
                        List<MultiTempWeather> weatherList = oAPI.fetchDailyWeather(new City(selectedCityLabel.getText()));
                        Optional<MultiTempWeather> weather = MultiTempWeather.getWeather(weatherList, date.get());
                        weather.ifPresentOrElse(w -> {
                            appScene.setWeather(w.morningTemperature.toInt(), w.dayTemperature.toInt());
                        }, () -> System.out.println("Météo introuvable"));
                    }
                    appScene.activate();
                }
            }
        });

        this.getChildren().addAll(leftLabel, listView, textField);
        this.setSpacing(25);

        this.setBackground(
                new Background(new BackgroundFill(Color.rgb(221, 221, 221), CornerRadii.EMPTY, Insets.EMPTY)));
    }

}
