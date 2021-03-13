package app.appmeteo;

import app.appmeteo.view.AppScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AppMeteo extends Application {

    @Override
    public void start(Stage window) throws Exception {
        Scene scene = new AppScene();
        scene.getStylesheets().addAll(
            getClass().getResource("/css/style.css").toExternalForm(),
            getClass().getResource("/css/center.css").toExternalForm()
        );
        window.getIcons().add(new Image("/app_icon.png"));
        window.initStyle(StageStyle.TRANSPARENT);
        window.setTitle("Project Z: AppMeteo");
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }

    public static void main(String[] args) { launch(args); }
}
