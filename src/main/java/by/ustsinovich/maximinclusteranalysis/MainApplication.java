package by.ustsinovich.maximinclusteranalysis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        var loader = new FXMLLoader(MainApplication.class.getResource("view/main-view.fxml"));
        var scene = new Scene(loader.load(), 640, 640);

        scene.getStylesheets().add(String.valueOf(MainApplication.class.getResource("style/main-view.css")));

        stage.setTitle("Maximin cluster analysis");
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}