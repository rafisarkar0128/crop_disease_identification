package com.crop.app.gui.view;

import com.crop.app.gui.controller.IntroPageController;
import com.crop.app.infrastructure.loader.ResourceLoader;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class IntroPage {

    public static Scene createHomePage() {

        Image logoImage = new Image(ResourceLoader.getIcon("logo-with-text.png"));
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(300);
        logoView.setFitHeight(300);

        Label welcomeLabel = new Label("Welcome to Crop Disease Identification Application");
        welcomeLabel
                .setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #1a5c1a;");
        welcomeLabel.setWrapText(true);

        Label subtitleLabel = new Label("Press Start to get started");
        subtitleLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #555555;");

        Button startButton = new Button("START");
        startButton.setStyle(
                "-fx-background-color: #2e7d32; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-pref-width: 150px; -fx-pref-height: 45px;");

        // Button click action
        startButton.setOnAction(IntroPageController.startButtonHandler);

        // Layout
        VBox root = new VBox(20);
        root.setStyle("-fx-background-color: white; -fx-alignment: center; -fx-padding: 40px;");
        root.getChildren().addAll(logoView, welcomeLabel, subtitleLabel, startButton);

        // Scene and Stage
        return new Scene(root, 600, 600);
    }
}
