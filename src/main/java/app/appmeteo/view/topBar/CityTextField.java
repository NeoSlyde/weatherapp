package app.appmeteo.view.topBar;

import javafx.scene.control.TextField;

public class CityTextField extends TextField {
    public CityTextField() {
        this.setPromptText("Ville");
        this.textProperty().addListener((o, v, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*"))
                this.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
        });
        this.getStyleClass().add("search");
        this.setPrefWidth(350);
    }
}
