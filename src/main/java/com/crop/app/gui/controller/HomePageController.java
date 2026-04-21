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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for the home page UI.
 *
 * <p>
 * Handles navigation and initial setup for the home page.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 19-04-2026
 */
public class HomePageController {
    /**
     * The primary stage of the application, used for scene navigation. Must be set before handling
     * any user interactions.
     */
    private Stage stage;

    /**
     * The name of the selected crop.
     */
    private String selectedCrop;

    /**
     * The background image for the home page.
     */
    @FXML
    private ImageView backgroundImage;

    /**
     * The header section of the home page, containing the search field and navigation buttons.
     */
    @FXML
    private HBox header;

    /**
     * The text field for entering or selecting a crop.
     */
    @FXML
    private TextField cropSearchField;

    /**
     * The label for displaying status messages to the user.
     */
    @FXML
    private Label statusLabel;

    /**
     * The panel for displaying informational messages, which can be toggled visible or hidden.
     */
    @FXML
    private StackPane infoPanel;

    /**
     * The panel containing settings options, which can be toggled visible or hidden.
     */
    @FXML
    private VBox settingsPanel;

    /**
     * Default constructor required for FXML loading. The primary stage must be set via
     * {@link #setStage} before any user interactions.
     */
    public HomePageController() {}

    /**
     * Creates a home page controller with an initialized primary stage.
     *
     * @param stage the primary stage
     */
    public HomePageController(Stage stage) {
        this.stage = Objects.requireNonNull(stage, "stage");
    }

    /**
     * Sets the primary stage used by this controller.
     *
     * @param stage the primary stage
     */
    public void setStage(Stage stage) {
        this.stage = Objects.requireNonNull(stage, "stage");
    }

    /**
     * Gets the primary stage used by this controller.
     *
     * @return the primary stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Ensures that the primary stage has been initialized before handling any user interactions.
     *
     * @throws IllegalStateException if the primary stage has not been initialized
     */
    private void ensureStage() {
        if (stage == null) {
            throw new IllegalStateException("Primary stage has not been initialized.");
        }
    }

    /**
     * Handles the news button action to show news related to crops and diseases.
     *
     * @param event the action event fired by the news button
     */
    @FXML
    private void showNews(ActionEvent event) {
        infoPanel.getChildren().clear();
        Label newsLabel = new Label("Latest News - Coming Soon!");
        newsLabel.getStyleClass().add("coming-soon");
        infoPanel.getChildren().add(newsLabel);
    }

    /**
     * Handles the feeds button action to show news related to crops and diseases.
     *
     * @param event the action event fired by the feeds button
     */
    @FXML
    private void showFeeds(ActionEvent event) {
        infoPanel.getChildren().clear();
        Label feedsLabel = new Label("Feeds - Coming Soon!");
        feedsLabel.getStyleClass().add("coming-soon");
        infoPanel.getChildren().add(feedsLabel);
    }

    /**
     * Handles the settings button action to show news related to crops and diseases.
     *
     * @param event the action event fired by the settings button
     */
    @FXML
    private void showSettings(ActionEvent event) {
        boolean isCurrentlyVisible = settingsPanel.isVisible();
        settingsPanel.setVisible(!isCurrentlyVisible);
        settingsPanel.setManaged(!isCurrentlyVisible);
    }

    /**
     * Handles crop quick selection from trending list.
     *
     * @param event the action event
     */
    private void selectCrop(String cropName) {
        ensureStage();
        this.selectedCrop = cropName;
        // stage.setScene(new DiseaseSelectionPage(stage, selectedCrop).createScene());
    }

    /**
     * Handles crop selection via button click.
     *
     * @param event the action event
     */
    @FXML
    private void handleCropSelect(ActionEvent event) {
        ensureStage();
        String cropName = cropSearchField.getText();

        if (cropName == null || cropName.trim().isEmpty()) {
            cropSearchField.setPromptText("PLEASE ENTER A CROP NAME!");

            if (!cropSearchField.getStyleClass().contains("search-field-error")) {
                cropSearchField.getStyleClass().add("search-field-error");
            }

            statusLabel.setText("");
            return;
        }

        cropSearchField.getStyleClass().remove("search-field-error");
        cropSearchField.setPromptText("Search crop...");

        this.selectedCrop = cropName.trim();
        statusLabel.setText("Selected: " + selectedCrop);
        // stage.setScene(new DiseaseSelectionPage(stage, selectedCrop).createScene());
    }

    /**
     * Handles crop selection via button click.
     *
     * @param event the action event
     */
    @FXML
    private void handleCropSelection(ActionEvent event) {
        ensureStage();
        selectCrop(event.getTarget().toString());
    }

    @FXML
    private void handleBackToHome(ActionEvent event) {
        ensureStage();
        // stage.setScene(new HomePage(stage).createScene());
    }

    /**
     * Handles the account info button action to show user account information.
     *
     * @param event the action event fired by the account info button
     */
    @FXML
    private void showAccountInfo(ActionEvent event) {
        infoPanel.getChildren().clear();
        Label accountInfoLabel = new Label("Account Info - Coming Soon!");
        accountInfoLabel.getStyleClass().add("coming-soon");
        infoPanel.getChildren().add(accountInfoLabel);
    }

    /**
     * Handles the notifications button action to show user notifications.
     *
     * @param event the action event fired by the notifications button
     */
    @FXML
    private void showNotifications(ActionEvent event) {
        infoPanel.getChildren().clear();
        Label notificationsLabel = new Label("Notifications - Coming Soon!");
        notificationsLabel.getStyleClass().add("coming-soon");
        infoPanel.getChildren().add(notificationsLabel);
    }

    /**
     * Handles the theme button action to show theme selection options.
     *
     * @param event the action event fired by the theme button
     */
    @FXML
    private void showThemeSettings(ActionEvent event) {
        infoPanel.getChildren().clear();
        Label themesLabel = new Label("Themes - Coming Soon!");
        themesLabel.getStyleClass().add("coming-soon");
        infoPanel.getChildren().add(themesLabel);
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        ensureStage();
        stage.setScene(new LoginPage(stage).createScene());
    }
}
