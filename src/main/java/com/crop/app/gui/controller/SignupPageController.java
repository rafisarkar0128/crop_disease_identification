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

import java.util.List;
import java.util.Objects;
import com.crop.app.domain.model.User;
import com.crop.app.domain.service.UserAuthService;
import com.crop.app.gui.view.LoginPage;
import com.crop.app.infrastructure.loader.UserDatabaseLoader;
import com.crop.app.infrastructure.persistence.JsonUserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the sign-up page UI.
 *
 * <p>
 * Handles user input validation for account registration fields and provides navigation back to the
 * login page.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 19-04-2026
 */
public class SignupPageController {

	/**
	 * Primary stage used for scene navigation.
	 */
	private Stage stage;

	/**
	 * Service that handles user authentication and registration operations.
	 */
	private UserAuthService authService;

	/**
	 * FXML-injected text field for username input.
	 */
	@FXML
	private TextField usernameField;

	/**
	 * FXML-injected text field for email input.
	 */
	@FXML
	private TextField emailField;

	/**
	 * FXML-injected password field for password input.
	 */
	@FXML
	private PasswordField passwordField;

	/**
	 * FXML-injected password field for confirm-password input.
	 */
	@FXML
	private PasswordField confirmPasswordField;

	/**
	 * FXML-injected label for showing status and validation messages.
	 */
	@FXML
	private Label statusLabel;

	/**
	 * Creates a sign-up page controller.
	 */
	public SignupPageController() {
		this.authService = initializeAuthService();
	}

	/**
	 * Creates a sign-up page controller with an initialized primary stage.
	 *
	 * @param stage the main application stage
	 * @throws NullPointerException if {@code stage} is null
	 */
	public SignupPageController(Stage stage) {
		this.stage = Objects.requireNonNull(stage, "stage");
		this.authService = initializeAuthService();
	}

	/**
	 * Initializes the authentication service with user data loaded from the database.
	 *
	 * @return initialized authentication service
	 */
	private UserAuthService initializeAuthService() {
		List<User> users = UserDatabaseLoader.loadUsersFromDatabase();
		return new UserAuthService(new JsonUserRepository(users));
	}

	/**
	 * Sets the primary stage used by this controller.
	 *
	 * @param stage the main application stage
	 * @throws NullPointerException if {@code stage} is null
	 */
	public void setStage(Stage stage) {
		this.stage = Objects.requireNonNull(stage, "stage");
	}

	/**
	 * Handles the sign-up button action.
	 *
	 * <p>
	 * Validates required fields, basic email format, and password confirmation.
	 *
	 * @param event the action event fired by the sign-up button
	 */
	@FXML
	private void handleSignUp(ActionEvent event) {
		ensureStage();

		String username = sanitize(usernameField.getText());
		String email = sanitize(emailField.getText());
		String password = passwordField.getText() == null ? "" : passwordField.getText();
		String confirmPassword =
				confirmPasswordField.getText() == null ? "" : confirmPasswordField.getText();

		if (username.isEmpty() || email.isEmpty() || password.isEmpty()
				|| confirmPassword.isEmpty()) {
			statusLabel.setText("Please fill in username, email, password, and confirm password.");
			return;
		}

		if (!email.contains("@") || email.startsWith("@") || email.endsWith("@")) {
			statusLabel.setText("Please enter a valid email address.");
			return;
		}

		if (!password.equals(confirmPassword)) {
			statusLabel.setText("Password and confirm password do not match.");
			return;
		}

		if (password.length() < 8) {
			statusLabel.setText("Password must be at least 8 characters long.");
			return;
		}

		if (authService.getUserByUsername(username) != null) {
			statusLabel.setText("Username already exists. Please choose another one.");
			return;
		}

		if (authService.getUserByEmail(email) != null) {
			statusLabel.setText("Email is already registered.");
			return;
		}

		try {
			User user = new User(generateNextUserId(), username, email, password);
			user.setBio("New user account. Welcome to Crop Disease Identification.");
			user.setAvatar("avatar-default.png");
			authService.register(user);
			stage.setScene(new LoginPage(stage).createScene());
		} catch (RuntimeException ex) {
			statusLabel.setText("Failed to create account: " + ex.getMessage());
		}
	}

	/**
	 * Handles the back-to-login action.
	 *
	 * @param event the action event fired by the back button
	 */
	@FXML
	private void handleBackToLogin(ActionEvent event) {
		ensureStage();
		stage.setScene(new LoginPage(stage).createScene());
	}

	/**
	 * Ensures that the primary stage has been initialized before scene-dependent actions.
	 *
	 * @throws IllegalStateException if the primary stage has not been initialized
	 */
	private void ensureStage() {
		if (stage == null) {
			throw new IllegalStateException("Primary stage has not been initialized.");
		}
	}

	private String sanitize(String value) {
		return value == null ? "" : value.trim();
	}

	/**
	 * Generates the next user id from existing users in the database.
	 *
	 * @return next user id as string
	 */
	private String generateNextUserId() {
		List<User> users = UserDatabaseLoader.loadUsersFromDatabase();
		int maxId = users.stream().map(User::getId).map(this::toIntOrZero).max(Integer::compareTo)
				.orElse(0);
		return String.valueOf(maxId + 1);
	}

	/**
	 * Safely parses a numeric id and returns 0 when invalid.
	 *
	 * @param value the raw id value
	 * @return parsed integer id or 0
	 */
	private int toIntOrZero(String value) {
		try {
			return Integer.parseInt(sanitize(value));
		} catch (NumberFormatException ex) {
			return 0;
		}
	}

}
