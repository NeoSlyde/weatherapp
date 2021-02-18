package app.appmeteo.view.TopBar;

import app.appmeteo.view.AppLabel;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

public class TopBarComponent extends HBox {    
    public TopBarComponent(Scene scene) {
        HBox middleSection = new MiddleSection(scene);
        Label meteoLogo = new AppLabel("Meteo", "title");
        meteoLogo.setPadding(new Insets(0, 0, 0, 12));
        this.getChildren().addAll(meteoLogo, middleSection);
        
        this.setSpacing(200);
        this.setPadding(new Insets(0, 0, 0, 20));
        this.setBackground(new Background(new BackgroundFill(Color.rgb(55, 55, 55), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setPrefHeight(70);
    }

    private static class MiddleSection extends HBox {
        MiddleSection(Scene scene) {
            this.setSpacing(20);
            this.setAlignment(Pos.CENTER);

            this.getChildren().add(new CityTextField());
            this.getChildren().add(new DateTextField());
            this.getChildren().add(new SearchButton(scene));
        }
    }
}
