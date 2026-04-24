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

public class SignupPageController {

	private Stage stage;

	private UserAuthService authService;

	@FXML
	private TextField usernameField;

	@FXML
	private TextField emailField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private PasswordField confirmPasswordField;

	@FXML
	private Label statusLabel;

	public SignupPageController() {
		this.authService = initializeAuthService();
	}

	public SignupPageController(Stage stage) {
		this.stage = Objects.requireNonNull(stage, "stage");
		this.authService = initializeAuthService();
	}

	private UserAuthService initializeAuthService() {
		List<User> users = UserDatabaseLoader.loadUsersFromDatabase();
		return new UserAuthService(new JsonUserRepository(users));
	}

	public void setStage(Stage stage) {
		this.stage = Objects.requireNonNull(stage, "stage");
	}

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

		if (password.length() < 4) {
			statusLabel.setText("Password must be at least 4 characters long.");
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

	@FXML
	private void handleBackToLogin(ActionEvent event) {
		ensureStage();
		stage.setScene(new LoginPage(stage).createScene());
	}

	private void ensureStage() {
		if (stage == null) {
			throw new IllegalStateException("Primary stage has not been initialized.");
		}
	}

	private String sanitize(String value) {
		return value == null ? "" : value.trim();
	}

	private String generateNextUserId() {
		List<User> users = UserDatabaseLoader.loadUsersFromDatabase();
		int maxId = users.stream().map(User::getId).map(this::toIntOrZero).max(Integer::compareTo)
				.orElse(0);
		return String.valueOf(maxId + 1);
	}

	private int toIntOrZero(String value) {
		try {
			return Integer.parseInt(sanitize(value));
		} catch (NumberFormatException ex) {
			return 0;
		}
	}

}
