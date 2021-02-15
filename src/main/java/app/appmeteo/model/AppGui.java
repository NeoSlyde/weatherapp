package app.appmeteo.model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class AppGui {

    public static BorderPane create() {
        BorderPane borderPane = new BorderPane();
        borderPane.getStyleClass().add("bg-1");
        borderPane.setPadding(new Insets(5));

        //TOP

        Label city = createLabel("Ville: ", "bg-2");
        TextField textFieldCity = new TextField ();
        textFieldCity.setPromptText("Entrez la ville.");
        HBox hb = new HBox();
        hb.getChildren().addAll(city, textFieldCity);
        hb.setSpacing(10);

        Label date = createLabel("Date: ", "bg-2");
        TextField textFieldDate = new TextField();
        textFieldDate.setPromptText("jj/MM/aaaa");
        hb.getChildren().addAll(date, textFieldDate);
        borderPane.setTop(hb);

        //LEFT
        Label left = createLabel("Left", "bg-3");

        BorderPane.setAlignment(left, Pos.BOTTOM_LEFT);
        borderPane.setLeft(left);

        Label center = createLabel("Center", "bg-4");

        BorderPane.setAlignment(center, Pos.TOP_CENTER);
        borderPane.setCenter(center);

        Label right = createLabel("Right", "bg-5");
        borderPane.setRight(right);

        Label bottom = createLabel("Bottom", "bg-6");
        borderPane.setBottom(bottom);

        return borderPane;
    }

    private static Label createLabel(String text, String styleClass) {
        Label label = new Label(text);
        label.getStyleClass().add(styleClass);
        BorderPane.setMargin(label, new Insets(5));
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return label;
    }

}
