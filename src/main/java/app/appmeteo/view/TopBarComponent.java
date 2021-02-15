package app.appmeteo.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TopBarComponent {
    static HBox create() {
        HBox hb = new HBox();
        hb.setSpacing(10);

        Label city = AppGui.createLabel("Ville: ", "bg-2");
        TextField textFieldCity = new TextField ();
        textFieldCity.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {
                textFieldCity.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });

        Label date = AppGui.createLabel("Date: ", "bg-2");
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

        Button btn = new Button("Valider");
        btn.setOnAction(event -> {
            CenterComponent.cityS = textFieldCity.getText();
            CenterComponent.dateS = textFieldDate.getText();
            System.out.println(CenterComponent.cityS + " " + CenterComponent.dateS);
        });

        hb.getChildren().addAll(city, textFieldCity, date, textFieldDate,btn);
        hb.setSpacing(5);
        hb.setAlignment(Pos.CENTER);

        return hb;
    }
}
