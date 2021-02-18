package app.appmeteo.view.TopBar;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class SearchButton extends Button {
    private static ImageView logo = new ImageView("valid_lres.png");
    public SearchButton() {
        super("");
        
        this.getStyleClass().add("imagebtn");
        this.setGraphic(logo);
        this.setPadding(new Insets(0, 0, 0, -5));
        this.setScaleX(.8);
        this.setScaleY(.8);

        this.setOnAction((evt) -> {
            System.out.println("cc");
        });
    }
}
