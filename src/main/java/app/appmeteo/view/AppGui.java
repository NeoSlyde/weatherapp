package app.appmeteo.view;

import app.appmeteo.view.TopBar.TopBarComponent;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class AppGui extends BorderPane {

    public AppGui() {
        this.getStyleClass().add("bg-1");
        this.setTop(new TopBarComponent());
        this.setLeft(LeftBarComponnent.create());
        this.setCenter(CenterComponent.create());
        this.setRight(RightBarComponnent.create());
    }

    public static Label createLabel(String text, String styleClass) {
        Label label = new Label(text);
        label.getStyleClass().add(styleClass);
        BorderPane.setMargin(label, new Insets(5));
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return label;
    }

}
