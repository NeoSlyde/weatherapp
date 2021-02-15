package app.appmeteo.view;

import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TopBarComponent {
    static HBox create() {
        HBox hb = new HBox();
        hb.setSpacing(10);

        Label city = AppGui.createLabel("Ville: ", "bg-2");
        TextField textFieldCity = new TextField ();
        textFieldCity.setPromptText("Entrez la ville.");

        Label date = AppGui.createLabel("Date: ", "bg-2");
        TextField textFieldDate = new TextField();
        textFieldDate.setPromptText("jj/MM/aaaa");

        hb.getChildren().addAll(city, textFieldCity, date, textFieldDate);

        return hb;
    }
}
