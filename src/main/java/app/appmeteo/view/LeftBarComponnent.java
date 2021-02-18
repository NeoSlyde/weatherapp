package app.appmeteo.view;

import app.appmeteo.model.City;
import app.appmeteo.model.Favorites;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.naming.Binding;
import java.util.ArrayList;
import java.util.Arrays;

public class LeftBarComponnent {
    static VBox create() {
        Favorites favorites = new Favorites();
        VBox vb = new VBox();



        Label leftLabel = AppGui.createLabel("Favoris", "bg-5");
        leftLabel.setAlignment(Pos.CENTER);
        leftLabel.setPrefWidth(250);
        // Button 1
        Button buttonAddCity = new Button("Ajouter");
        Button buttonSelectedCity = new Button("Selectionner");
        Button buttonRemoveCity = new Button("Supprimer");

        // TextField
        TextField textField = new TextField();
        textField.setPromptText("Ajouter une ville favorite");
        textField.setMaxWidth(240);

        //ListView
        ListView<City> listView = new ListView<>();
        listView.setMaxWidth(240);

        buttonSelectedCity.setOnAction(event -> {
            City selectedCity = listView.getSelectionModel().getSelectedItem();
            System.out.println("Tu as selectionne : " + selectedCity.toString());

        });
        buttonRemoveCity.setOnAction(event -> {
            if(!listView.getItems().isEmpty()){
                int selectedIndices = listView.getSelectionModel().getSelectedIndex();
                City selectedCity = listView.getSelectionModel().getSelectedItem();
                favorites.remove(selectedCity);
                listView.getItems().remove(selectedIndices);
            }
        });

        buttonAddCity.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!textField.getText().trim().isEmpty()) {
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
                        listView.getItems().add(city);
                    }
                    System.out.println(Arrays.toString(favorites.getList().toArray()));
                }

            }
        });
        vb.getChildren().addAll(leftLabel,buttonAddCity,textField,listView,buttonSelectedCity,buttonRemoveCity);
        vb.setSpacing(10);
        vb.setPadding(new Insets(-50, 0, 10, 0));

        vb.setBackground(new Background(new BackgroundFill(Color.rgb(221,221,221), CornerRadii.EMPTY, Insets.EMPTY)));
        vb.setAlignment(Pos.CENTER);


        return vb;
    }
}
