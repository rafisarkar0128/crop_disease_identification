package com.crop.app.gui.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import com.crop.app.domain.model.User;
import com.crop.app.domain.service.UserAuthService;
import com.crop.app.gui.view.HomePage;
import com.crop.app.gui.view.SignupPage;
import com.crop.app.infrastructure.loader.UserDatabaseLoader;
import com.crop.app.infrastructure.persistence.JsonUserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginPageController {

    private Stage stage;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    private UserAuthService authService;

    public LoginPageController() {
        this.authService = initializeAuthService();
    }

    public LoginPageController(Stage stage) throws NullPointerException {
        this.stage = Objects.requireNonNull(stage, "stage");
        this.authService = initializeAuthService();
    }

    private UserAuthService initializeAuthService() {
        try {
            List<User> users = UserDatabaseLoader.loadUsersFromDatabase();
            JsonUserRepository repository = new JsonUserRepository(users);
            return new UserAuthService(repository);
        } catch (Exception e) {
            System.err.println("Failed to initialize auth service: " + e.getMessage());
            return new UserAuthService(new JsonUserRepository(Arrays.asList()));
        }
    }

    public void setStage(Stage stage) throws NullPointerException {
        this.stage = Objects.requireNonNull(stage, "stage");
    }

    public Stage getStage() {
        return stage;
    }

    private void ensureStage() {
        if (stage == null) {
            throw new IllegalStateException("Primary stage has not been initialized.");
        }
    }

    private String sanitize(String value) {
        return value == null ? "" : value.trim();
    }

    @FXML
    private void handleSignIn(ActionEvent event) {
        ensureStage();

        String username = sanitize(usernameField.getText());
        String password = sanitize(passwordField.getText());

        if (username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Enter both your username and password.");
            return;
        }

        if (authService.authenticate(username, password)) {
            statusLabel.setText("Sign-in successful!");
            stage.setScene(
                    new HomePage(stage, authService.getUserByUsername(username)).createScene());
        } else {
            statusLabel.setText("Invalid username or password.");
        }
    }

    @FXML
    private void toSignUpPage(ActionEvent event) {
        ensureStage();
        stage.setScene(new SignupPage(stage).createScene());
    }
}
