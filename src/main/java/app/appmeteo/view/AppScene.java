package app.appmeteo.view;

import app.appmeteo.view.center.CenterComponent;
import app.appmeteo.view.topBar.TopBarComponent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class AppScene extends Scene {
    public AppScene() {
        super(new BorderPane(), 1200, 800);
        BorderPane layout = (BorderPane) this.getRoot();

        layout.getStyleClass().add("bg-1");
        layout.setTop(new TopBarComponent(this));
        layout.setLeft(new LeftBarComponent(this));
        layout.setCenter(new CenterComponent(this));
        layout.setRight(new RightBarComponnent(this));
    }
}
