package app.appmeteo.view;

import javafx.scene.control.Label;

public class AppLabel extends Label {
    public AppLabel(String text, String styleClass) {
        super(text);
        this.getStyleClass().add(styleClass);
        this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }
}
