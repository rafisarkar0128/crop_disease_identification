package com.crop.app.gui.view;

import com.crop.app.infrastructure.loader.ImageLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SignInPage {

    public Stage primaryStage;

    public SignInPage() {}

    public SignInPage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Scene createPage() {
        // Background Image
        ImageView backgroundView = ImageLoader.getImageAsImageView("background.png");

        // Welcome Text
        Label username = new Label("Enter your username/email");
        username.setFont(Font.font("Verdana", 14));
        username.setTextFill(Color.web("#555555"));

        // Subtitle Text
        Label password = new Label("Enter your password");
        password.setFont(Font.font("Verdana", 14));
        password.setTextFill(Color.web("#555555"));

        // Sign in Buttons
        Button signInButton = new Button("Sign In");
        signInButton.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        signInButton.setBackground(Background.fill(Color.web("#2e7d32")));
        signInButton.setTextFill(Color.WHITE);
        signInButton.setPrefHeight(45);
        signInButton.setPrefWidth(150);
        // signInButton.setOnAction(SignInPageController.signInButtonHandler);

        // Sign up Button
        Button guestLoginButton = new Button("Guest Login");
        guestLoginButton.setBackground(Background.fill(Color.web("#555555")));
        guestLoginButton.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        guestLoginButton.setTextFill(Color.WHITE);
        guestLoginButton.setPrefHeight(45);
        guestLoginButton.setPrefWidth(150);
        // guestLoginButton.setOnAction(SignInPageController.guestLoginButtonHandler);

        // Button Container
        HBox buttonBox = new HBox(20, signInButton, guestLoginButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Root Container
        VBox root = new VBox(20);
        root.setStyle("-fx-alignment: center; -fx-padding: 40px;");
        root.getChildren().addAll(username, password, buttonBox);

        // StackPane to hold background and content
        StackPane stackpane = new StackPane();
        stackpane.getChildren().addAll(backgroundView, root);

        // Scene and Stage
        Scene introPageScene = new Scene(stackpane, 800, 600);
        return introPageScene;
    }

    // public static
}
