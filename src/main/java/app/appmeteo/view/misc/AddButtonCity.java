package app.appmeteo.view.misc;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class AddButtonCity extends Button {
    private boolean clickable = true;
    public boolean isClickable() { return clickable; }
    public void setClickable(boolean v) {
        this.setOpacity(v ? 1 : .3);
        clickable = v;
    }

    public ImageView img = new ImageView("add.png");

    public AddButtonCity(Scene scene, Action onClick, double scale) {
        super("");

        img.setPreserveRatio(true);
        img.setFitWidth(50);
        img.setFitHeight(50);

        this.getStyleClass().add("imagebtn");
        this.setGraphic(img);
        this.setPadding(new Insets(0, 0, 0, -3));
        this.setScaleX(scale);
        this.setScaleY(scale);

        this.setOnAction((evt) -> {
            if (clickable)
                onClick.run();
        });
        this.hoverProperty().addListener((o, oldVal, newVal) -> {
            if (newVal && clickable) {
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
