package app.appmeteo.view.leftBar;

import app.appmeteo.controller.OpenWeatherMapAPI;
import app.appmeteo.model.*;
import app.appmeteo.view.AppScene;
import app.appmeteo.view.misc.AppLabel;
import app.appmeteo.view.rightBar.RightBarComponnent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.*;
import java.time.LocalDate;
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

        Button buttonAddCurrentCity = new Button("Ajouter la ville actuelle");
        buttonAddCurrentCity.setCursor(Cursor.HAND);
        buttonAddCurrentCity.setMaxWidth(240);
        buttonAddCurrentCity.getStyleClass().add("addCityCurrent");
        buttonAddCurrentCity.setPadding(new Insets(10,0,0,0));

        // ListView
        listView.setMaxWidth(240);
        listView.setPrefHeight(430);

        for(City city : favorites.getList()){
            Label cityLabel = new AppLabel(city.toString(), "favorites-item-label");
            listView.getItems().add(cityLabel);
            cityLabel.setPadding(new Insets(0, 0, 15, 25));
            RightBarComponnent.addLabel(city);
        }

        buttonAddCurrentCity.setOnAction(new EventHandler<ActionEvent>() {
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

                    Optional<LocalDate> date = appScene.getCenterDate();
                    date.ifPresent(d -> appScene.setWeather(new City(selectedCityLabel.getText()), d));
                    appScene.activate();
                }
            }
        });

        this.getChildren().addAll(new FavoriteLabel(appScene), listView,buttonAddCurrentCity/*, textField*/);
        this.setSpacing(25);

        this.setBackground(
                new Background(new BackgroundFill(Color.rgb(221, 221, 221), CornerRadii.EMPTY, Insets.EMPTY)));
    }

}
