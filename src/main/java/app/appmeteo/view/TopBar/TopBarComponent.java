package app.appmeteo.view.TopBar;

import app.appmeteo.view.AppGui; // TODO: replace createLabel
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

public class TopBarComponent extends HBox {    
    public TopBarComponent() {
        HBox middleSection = new MiddleSection();
        Label meteoLogo = AppGui.createLabel("Meteo", "title");
        this.getChildren().addAll(meteoLogo, middleSection);
        
        this.setSpacing(130);
        this.setPadding(new Insets(0, 0, 0, 20));
        this.setBackground(new Background(new BackgroundFill(Color.rgb(55, 55, 55), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setPrefHeight(60);
    }

    private static class MiddleSection extends HBox {
        MiddleSection() {
            this.setSpacing(0);
            this.setAlignment(Pos.CENTER);

            Label cityPadding = AppGui.createLabel("", "bg-2");
            TextField cityTextField = new CityTextField();
            this.getChildren().addAll(cityPadding, cityTextField);

            Label datePadding = AppGui.createLabel("", "bg-2");
            TextField dateTextField = new DateTextField();
            this.getChildren().addAll(datePadding, dateTextField);
            
            Button searchButton = new SearchButton();
            this.getChildren().add(searchButton);
        }
    }
}
