package app.appmeteo.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class TopBarComponent {
    static HBox create() {
        HBox hb = new HBox();
        hb.setSpacing(0);

        Label meteo = AppGui.createLabel("Meteo", "title");
        Label city = AppGui.createLabel("", "bg-2");
        TextField textFieldCity = new TextField ();
        textFieldCity.setPromptText("Ville");
        textFieldCity.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                textFieldCity.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });

        Label date = AppGui.createLabel("", "bg-2");
        TextField textFieldDate = new TextField();
        textFieldDate.setPromptText("jj/MM/aaaa");
        textFieldDate.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length() == 2 && oldValue.length() == 1){
                textFieldDate.setText(newValue + "/");
            }
            if(newValue.length() == 5 && oldValue.length() == 4){
                textFieldDate.setText(newValue + "/");
            }
            if(newValue.length() > 10 && oldValue.length() == 10){
                textFieldDate.setText(oldValue);
            }
        });
        textFieldCity.getStyleClass().add("search");
        textFieldDate.getStyleClass().add("search");

        ImageView image = new ImageView("valid.png");
        ImageView image2 = new ImageView("search.png");
        Button btn = new Button("");
        btn.getStyleClass().add("imagebtn");
        btn.setGraphic(image);
        btn.setOnAction(event -> {
            btn.setGraphic(image2);
            btn.setGraphic(image);
        });
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(city, textFieldCity, date, textFieldDate,btn);
        HBox hb2 = new HBox();
        hb2.getChildren().addAll(meteo,hb);
        hb2.setSpacing(130);
        hb2.setPadding(new Insets(0,0,0,20));


        hb2.setBackground(new Background(new BackgroundFill(Color.rgb(55, 55, 55), CornerRadii.EMPTY, Insets.EMPTY)));
        hb2.setPrefHeight(60);

        return hb2;
    }
}
