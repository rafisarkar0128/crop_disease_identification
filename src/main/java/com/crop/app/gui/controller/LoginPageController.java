/*
 * Crop Disease Identification
 *
 * Copyright 2026-Present Md. Rafi Sarkar (rafisarkar0128), and contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

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

/**
 * Controller for the login page UI.
 *
 * <p>
 * Handles user interactions on the login page, including sign-in and navigation to the sign-up
 * page.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 19-04-2026
 */
public class LoginPageController {
    /**
     * The primary stage of the application, used for scene navigation. Must be set before handling
     * any user interactions.
     */
    private Stage stage;

    /**
     * FXML-injected text field for username input.
     */
    @FXML
    private TextField usernameField;

    /**
     * FXML-injected password field for password input.
     */
    @FXML
    private PasswordField passwordField;

    /**
     * FXML-injected label for showing status and validation messages.
     */
    @FXML
    private Label statusLabel;

    /**
     * User authentication service for credential verification.
     */
    private UserAuthService authService;

    /**
     * Default constructor required for FXML loading. The primary stage must be set separately using
     * {@link #setStage(Stage)} before handling any user interactions.
     */
    public LoginPageController() {
        this.authService = initializeAuthService();
    }

    /**
     * Constructor that initializes the controller with the primary stage. The provided stage is
     * used for scene navigation when handling user interactions. The stage must not be null.
     *
     * @param stage the primary stage of the application
     * @throws NullPointerException if the provided stage is null
     */
    public LoginPageController(Stage stage) throws NullPointerException {
        this.stage = Objects.requireNonNull(stage, "stage");
        this.authService = initializeAuthService();
    }

    /**
     * Initializes the user authentication service by loading the user database.
     *
     * @return the initialized UserAuthService
     */
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

    /**
     * Sets the primary stage for this controller. The stage is used for scene navigation when
     * handling user interactions. This method must be called before handling any user interactions
     * that require stage access (e.g., sign-in or sign-up actions). The provided stage must not be
     * null.
     *
     * @param stage the primary stage of the application
     * @throws NullPointerException if the provided stage is null
     */
    public void setStage(Stage stage) throws NullPointerException {
        this.stage = Objects.requireNonNull(stage, "stage");
    }

    /**
     * Returns the primary stage currently associated with this controller.
     *
     * @return the primary stage, or {@code null} if it has not been set yet
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Ensures that the primary stage is available before scene-dependent actions are executed.
     *
     * @throws IllegalStateException if the primary stage has not been initialized
     */
    private void ensureStage() {
        if (stage == null) {
            throw new IllegalStateException("Primary stage has not been initialized.");
        }
    }

    /**
     * Sanitizes input by trimming whitespace and converting null to empty string.
     *
     * @param value the input string to sanitize
     * @return the sanitized string, or empty string if the input was null
     */
    private String sanitize(String value) {
        return value == null ? "" : value.trim();
    }

    /**
     * Handles the sign-in button action.
     *
     * <p>
     * Validates credentials and authenticates against the user database. On successful
     * authentication, navigates to the home page. Otherwise, displays an error message.
     *
     * @param event the action event fired by the sign-in button
     */
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
            stage.setScene(new HomePage(stage).createScene());
        } else {
            statusLabel.setText("Invalid username or password.");
        }
    }

    /**
     * Handles the sign-up button action to navigate to the sign-up page.
     *
     * <p>
     * Navigates to the dedicated sign-up page scene.
     *
     * @param event the action event fired by the sign-up button
     */
    @FXML
    private void toSignUpPage(ActionEvent event) {
        ensureStage();
        stage.setScene(new SignupPage(stage).createScene());
    }
}
