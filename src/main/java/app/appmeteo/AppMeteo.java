package app.appmeteo;

import app.appmeteo.view.AppGui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class AppMeteo extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(AppGui.create(), 1200,800);
        String styleSheet = getClass().getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(styleSheet);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }
}
