package app.appmeteo.view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class RightBarComponnent {
    static VBox create(){
        VBox vb = new VBox();
        vb.setSpacing(10);
        Label rightLabel = AppGui.createLabel("Information ", "bg-3");
        rightLabel.setPrefWidth(250);
        vb.getChildren().add(rightLabel);
        return vb;
    }

}
