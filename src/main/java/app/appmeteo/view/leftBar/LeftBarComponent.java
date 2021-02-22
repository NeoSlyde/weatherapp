package app.appmeteo.view.leftBar;

import app.appmeteo.model.*;
import app.appmeteo.view.misc.AppLabel;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class LeftBarComponent extends VBox {
    public LeftBarComponent(Scene scene) {
        Favorites favorites = new Favorites();

        Label leftLabel = new AppLabel("Favoris", "bg-5");
        leftLabel.setAlignment(Pos.CENTER);
        leftLabel.setPrefWidth(250);

        // TextField
        TextField textField = new TextField();
        textField.setPromptText("Ajouter une ville favorite");
        textField.setMaxWidth(240);

        textField.getStyleClass().add("addCity");

        //ListView
        ListView<City> listView = new ListView<>();
        listView.setMaxWidth(240);

        textField.setOnKeyReleased(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER){
                City city = new City(textField.getText());
                boolean success = false;
                for (City city1 : favorites.getList()){
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
            }
        });

        listView.setOnKeyReleased(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.DELETE){
                int selectedIndices = listView.getSelectionModel().getSelectedIndex();
                City selectedCity = listView.getSelectionModel().getSelectedItem();
                favorites.remove(selectedCity);
                listView.getItems().remove(selectedIndices);
            }
        });

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!listView.getItems().isEmpty()) {
                    City selectedCity = listView.getSelectionModel().getSelectedItem();
                    System.out.println("Tu as selectionne : " + selectedCity.toString());
                }
            }
        });


        this.getChildren().addAll(leftLabel,listView,textField);
        this.setSpacing(25);
        this.setPadding(new Insets(-50, 0, 10, 0));

        this.setBackground(new Background(new BackgroundFill(Color.rgb(221,221,221), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setAlignment(Pos.CENTER);
    }
}
