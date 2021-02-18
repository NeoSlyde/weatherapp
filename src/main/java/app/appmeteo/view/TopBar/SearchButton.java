package app.appmeteo.view.TopBar;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class SearchButton extends Button {
    private void onAction() {
        // TODO
        System.out.println("Searching ... (non en fait mais osef)");
    }

    private static ImageView logo = new ImageView("valid_lres.png");
    public SearchButton(Scene scene) {
        super("");
        
        this.getStyleClass().add("imagebtn");
        this.setGraphic(logo);
        this.setPadding(new Insets(0, 0, 0, -5));
        this.setScaleX(.8);
        this.setScaleY(.8);

        this.setOnAction((evt) -> { onAction(); });
        this.hoverProperty().addListener((o, oldVal, newVal) -> {
            if (newVal) {
                scene.setCursor(Cursor.HAND);
                this.setScaleX(1);
                this.setScaleY(1);
            } else {
                scene.setCursor(Cursor.DEFAULT);
                this.setScaleX(.8);
                this.setScaleY(.8);
            }
        });
    }
}
