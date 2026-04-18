package com.crop.app.gui.controller;

import javafx.stage.Stage;
import com.crop.app.gui.view.SignInPage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class IntroPageController {
    private Stage primaryStage;

    public IntroPageController() {}

    public IntroPageController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Stage getStage() {
        return primaryStage;
    }

    public EventHandler<ActionEvent> signInButtonHandler = e -> {
        System.out.println("Signing in...");
        primaryStage.setScene(new SignInPage(primaryStage).createPage());
    };
}
