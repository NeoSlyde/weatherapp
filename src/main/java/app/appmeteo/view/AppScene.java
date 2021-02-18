package app.appmeteo.view;

import app.appmeteo.view.TopBar.TopBarComponent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class AppScene extends Scene {
    public AppScene() {
        super(new BorderPane(), 1200, 800);
        BorderPane layout = (BorderPane) this.getRoot();

        layout.getStyleClass().add("bg-1");
        layout.setTop(new TopBarComponent());
        layout.setLeft(new LeftBarComponent());
        layout.setCenter(new CenterComponent());
        layout.setRight(new RightBarComponnent());
    }
}
