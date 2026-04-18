package com.crop.app.gui.controller;

import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SignInPageController {
    private Stage stage;

    public SignInPageController() {}

    public SignInPageController(Stage stage) {
        this.stage = stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public static EventHandler<ActionEvent> signInButtonHandler = e -> {
        System.out.println("Signing in...");
        // stage.setScene(SignInPage.createHomePage());
    };

    public static EventHandler<ActionEvent> signUpButtonHandler = e -> {
        System.out.println("Signing up...");
        // stage.setScene(SignUpPage.createSignUpPage());
    };
}
