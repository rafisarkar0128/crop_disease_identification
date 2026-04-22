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
import com.crop.app.domain.model.User;
import com.crop.app.infrastructure.loader.IconLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

/**
 * Controller for the account page UI.
 *
 * <p>
 * Handles navigation and initial setup for the account page.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 22-04-2026
 */
public class AccountInfoPageController {
    /**
     * The currently logged-in user.
     */
    private User currentUser;

    /**
     * The background image for the home page.
     */
    @FXML
    private ImageView accountAvatar;

    /**
     * The username label for the account page.
     */
    @FXML
    private Label accountUsername;

    /**
     * The email label for the account page.
     */
    @FXML
    private Label accountEmail;

    /**
     * The background image for the home page.
     */
    @FXML
    private Label accountBio;

    /**
     * Default constructor required for FXML loading.
     */
    public AccountInfoPageController() {}

    /**
     * Creates an account info controller with an initialized user.
     *
     * @param currentUser the currently logged-in user
     * @throws NullPointerException if {@code currentUser} is null
     */
    public AccountInfoPageController(User currentUser) {
        this.currentUser = Objects.requireNonNull(currentUser, "currentUser");
    }

    /**
     * Sets the currently logged-in user for this controller.
     *
     * @param currentUser the user to set as currently logged in
     */
    public void setCurrentUser(User currentUser) {
        this.currentUser = Objects.requireNonNull(currentUser, "currentUser");
    }

    /**
     * Gets the currently logged-in user for this controller.
     *
     * @return the currently logged-in user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Initializes the account info page with the current user's information.
     *
     * @throws IllegalStateException if the current user is not set before initialization
     */
    public void init() {
        if (currentUser == null) {
            throw new IllegalStateException(
                    "Current user must be set before initializing the account info page.");
        }


        // Set the account avatar image and apply a circular clip to make it round
        Circle clip = new Circle(50);
        accountAvatar.setImage(IconLoader.getIconAsImage(currentUser.getAvatar()));
        // accountAvatar.setClip(clip);
        accountAvatar.setPreserveRatio(true);
        accountAvatar.setSmooth(true);
        accountAvatar.setCache(true);

        // Setting the account information labels
        accountUsername.setText(currentUser.getUsername());
        accountEmail.setText(currentUser.getEmail());

        // Setting the account bio label with proper wrapping and width constraints
        accountBio.setText(currentUser.getBio());
        accountBio.setWrapText(true);
        // Add some padding
        accountBio.setMaxWidth(450);
    }
}
