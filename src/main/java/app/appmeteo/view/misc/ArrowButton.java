package app.appmeteo.view.misc;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class ArrowButton extends Button {
    Action onClick;

    private double scale;
    public ImageView img = new ImageView("valid_mres.png");

    public ArrowButton(Scene scene, Action onClick, double scale) {
        super("");
        this.scale = scale;
        
        img.setPreserveRatio(true);
        img.setFitWidth(50);
        img.setFitHeight(50);

        this.getStyleClass().add("imagebtn");
        this.setGraphic(img);
        this.setPadding(new Insets(0, 0, 0, -3));
        this.setScaleX(scale);
        this.setScaleY(scale);

        this.setOnAction((evt) -> { onClick.run(); });
        this.hoverProperty().addListener((o, oldVal, newVal) -> {
            if (newVal) {
                scene.setCursor(Cursor.HAND);
                this.setScaleX(scale * 1.2);
                this.setScaleY(scale * 1.2);
            } else {
                scene.setCursor(Cursor.DEFAULT);
                this.setScaleX(scale);
                this.setScaleY(scale);
            }
        });
    }

    public static interface Action {
        void run();
    }
}
