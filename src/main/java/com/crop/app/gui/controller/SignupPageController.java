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

import java.util.Objects;
import com.crop.app.gui.view.LoginPage;
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
	public SignupPageController() {}

	/**
	 * Creates a sign-up page controller with an initialized primary stage.
	 *
	 * @param stage the main application stage
	 * @throws NullPointerException if {@code stage} is null
	 */
	public SignupPageController(Stage stage) {
		this.stage = Objects.requireNonNull(stage, "stage");
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

		statusLabel.setText("Sign-up is ready, but backend registration is not connected yet.");
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
}
