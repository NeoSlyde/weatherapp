package app.appmeteo.view.rightBar;

import app.appmeteo.model.City;
import app.appmeteo.view.misc.AppLabel;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class RightBarComponnent extends HBox {

    static ListView<VBox> listView = new ListView<>();

    static VBox vb = new VBox();


    public RightBarComponnent(Scene scene) {
        listView.setPrefHeight(650);

        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);

        this.getChildren().add(listView);
        this.getChildren().add(0, separator);
    }

    public static void addLabel(City city){
        listView.getItems().add(new RightBarLabel(city));
    }

    public static void remove(int indice){
        listView.getItems().remove(indice);
    }

}

