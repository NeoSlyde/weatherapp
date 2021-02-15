package app.appmeteo.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class AppGui {

    public static BorderPane create() {
        BorderPane borderPane = new BorderPane();
        borderPane.getStyleClass().add("bg-1");
        borderPane.setPadding(new Insets(5));

        //TOP

        borderPane.setTop(TopBarComponent.create());

        //LEFT
        borderPane.setLeft(LeftBarComponnent.create());

        //CENTER
        borderPane.setCenter(CenterComponent.create());
        //RIGHT
        borderPane.setRight(RightBarComponnent.create());

        Label bottom = createLabel("Bottom", "bg-6");
        borderPane.setBottom(bottom);

        return borderPane;
    }

    public static Label createLabel(String text, String styleClass) {
        Label label = new Label(text);
        label.getStyleClass().add(styleClass);
        BorderPane.setMargin(label, new Insets(5));
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return label;
    }

}
