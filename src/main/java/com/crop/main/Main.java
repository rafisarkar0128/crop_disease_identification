package com.crop.main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.IOException;

/**
 * JavaFX App
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        Scene scene = new Scene(root, 640, 480, Color.PINK);

        stage.setTitle("Crop Identification System");
        stage.getIcons().add(new Image("file:src/main/resources/icons/app_logo.png"));
        stage.setScene(scene);
        stage.show();
    }
}
