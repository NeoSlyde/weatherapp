package app.appmeteo.view.leftBar;

import app.appmeteo.controller.OpenWeatherMapAPI;
import app.appmeteo.model.City;
import app.appmeteo.view.AppScene;
import app.appmeteo.view.misc.AddButtonCity;
import app.appmeteo.view.misc.AppLabel;
import app.appmeteo.view.rightBar.RightBarComponnent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;


public class FavoriteLabel extends VBox {
    public FavoriteLabel(Scene scene){
        HBox hBox = new HBox();
        hBox.setBackground(new Background(new BackgroundFill(Color.web("#c4c4c4"),
                CornerRadii.EMPTY,
                Insets.EMPTY)));

        Label favoriteLabel = new AppLabel("Favoris","bg-5");
        favoriteLabel.setPadding(new Insets(20,35,20,25));

        Button button = new AddButtonCity(scene, ()->{
            Stage obj = (Stage) this.getScene().getWindow();
            obj.setIconified(true);
        }, 0.5);
        button.setPadding(new Insets(20,0,19,0));
        button.setCursor(Cursor.HAND);

        TextInputDialog td = new TextInputDialog("");
        td.setHeaderText("Enter a city");


        EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                td.showAndWait().map(City::new).ifPresent(city -> {
                    try {
                        if (!OpenWeatherMapAPI.singleton.fetchCityExists(city.toString())) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Erreur");
                            alert.setHeaderText("Chargement de la meteo");
                            alert.setContentText("La ville `" + city + "` n'existe pas");
                            alert.showAndWait();
    
                            return;
                        }
                        boolean success = false;
                        for (City city1 : LeftBarComponent.favorites.getList()) {
                            if (city.equals(city1)) {
                                success = false;
                                break;
                            }
                            success = true;
                        }
                        if (LeftBarComponent.favorites.getList().isEmpty() || success) {
                            LeftBarComponent.favorites.add(city);
                            Label cityLabel = new AppLabel(city.toString(), "favorites-item-label");
                            LeftBarComponent.listView.getItems().add(cityLabel);
                            cityLabel.setPadding(new Insets(0, 0, 15, 25));
    
                            try {
                                LeftBarComponent.favorites.writeFavorite2File();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
    
                            RightBarComponnent.addLabel(city);
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });
            }
        };
        button.setOnAction(handler);

        hBox.getChildren().addAll(favoriteLabel,button);
        this.getChildren().add(hBox);
    }

}
