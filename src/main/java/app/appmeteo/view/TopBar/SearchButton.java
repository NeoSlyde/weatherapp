package app.appmeteo.view.TopBar;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class SearchButton extends Button {
    private static ImageView logo = new ImageView("valid_lres.png");
    public SearchButton() {
        super("");
        
        this.getStyleClass().add("imagebtn");
        this.setGraphic(logo);
    }
}
