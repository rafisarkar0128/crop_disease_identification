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

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import com.crop.app.common.exception.FxmlLoaderException;
import com.crop.app.common.exception.ResourceLoaderException;
import com.crop.app.domain.model.Crop;
import com.crop.app.domain.model.Disease;
import com.crop.app.domain.model.User;
import com.crop.app.domain.service.CropCatalogService;
import com.crop.app.gui.view.LoginPage;
import com.crop.app.infrastructure.loader.FxmlLoader;
import com.crop.app.infrastructure.loader.ImageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
     * Maximum number of crops to show in the search results.
     */
    private static final int MAX_SEARCH_RESULTS = 6;

    /**
     * Fixed width for search-result cards.
     */
    private static final double SEARCH_RESULT_CARD_WIDTH = 560.0;

    /**
     * Fixed height for search-result cards.
     */
    private static final double SEARCH_RESULT_CARD_HEIGHT = 96.0;

    /**
     * Maximum number of symptom suggestions in autocomplete list.
     */
    private static final int MAX_SYMPTOM_SUGGESTIONS = 12;

    /**
     * Crop catalog/search/symptom service used by this controller.
     */
    private final CropCatalogService cropCatalogService = new CropCatalogService();

    /**
     * The primary stage of the application, used for scene navigation. Must be set before handling
     * any user interactions.
     */
    private Stage stage;

    /**
     * The currently logged-in user.
     */
    private User currentUser;

    /**
     * The selected crop metadata used by disease-identification mode.
     */
    private Crop selectedCropData;

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
     * The home button in the header for navigation.
     */
    @FXML
    private Button homeButton;

    /**
     * The news button in the header for navigation.
     */
    @FXML
    private Button newsButton;

    /**
     * The feeds button in the header for navigation.
     */
    @FXML
    private Button feedsButton;

    /**
     * The settings button in the header for navigation.
     */
    @FXML
    private Button settingsButton;

    /**
     * The text field for entering or selecting a crop.
     */
    @FXML
    private TextField cropSearchField;

    /**
     * The panel for displaying trending crops, which can be toggled visible or hidden.
     */
    @FXML
    private VBox trendingPanel;

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
     * Tracks whether search mode is currently active.
     */
    private boolean searchModeActive;

    /**
     * Remembers whether the settings panel was visible before search mode started.
     */
    private boolean settingsPanelVisibleBeforeSearch;

    /**
     * Default constructor required for FXML loading. The primary stage must be set via
     * {@link #setStage} before any user interactions.
     */
    public HomePageController() {}

    /**
     * Creates a home page controller with an initialized primary stage.
     *
     * @param stage the primary stage
     * @param currentUser the currently logged-in user
     * @throws NullPointerException if either {@code stage} or {@code currentUser} is null
     */
    public HomePageController(Stage stage, User currentUser) {
        this.stage = Objects.requireNonNull(stage, "stage");
        this.currentUser = Objects.requireNonNull(currentUser, "currentUser");
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
     * Restores the default info panel content.
     */
    private void showDefaultInfoPanel() {
        Label defaultLabel = new Label("ADVERTISEMENT");
        defaultLabel.getStyleClass().add("ad-label");

        infoPanel.getChildren().clear();
        infoPanel.getChildren().add(defaultLabel);
        StackPane.setAlignment(defaultLabel, Pos.CENTER);
    }

    /**
     * Restores the header buttons when returning to the home layout.
     */
    private void showHomeNavigation() {
        if (homeButton != null) {
            homeButton.setVisible(true);
            homeButton.setManaged(true);
        }

        if (newsButton != null) {
            newsButton.setVisible(true);
            newsButton.setManaged(true);
        }

        if (feedsButton != null) {
            feedsButton.setVisible(true);
            feedsButton.setManaged(true);
        }

        if (settingsButton != null) {
            settingsButton.setVisible(true);
            settingsButton.setManaged(true);
        }
    }

    /**
     * Restores the home-page layout from search mode.
     */
    private void restoreHomeLayout() {
        searchModeActive = false;

        trendingPanel.setVisible(true);
        trendingPanel.setManaged(true);

        settingsPanel.setVisible(settingsPanelVisibleBeforeSearch);
        settingsPanel.setManaged(settingsPanelVisibleBeforeSearch);

        showHomeNavigation();
    }

    /**
     * Handles the home button action to navigate back to the home page.
     *
     * @param event the action event fired by the home button
     */
    @FXML
    private void showHome(ActionEvent event) {
        try {
            restoreHomeLayout();
            cropSearchField.setPromptText("Search crop...");

            if (cropSearchField.getStyleClass().contains("search-field-error")) {
                cropSearchField.getStyleClass().remove("search-field-error");
            }

            showDefaultInfoPanel();
        } catch (Exception e) {
            throw new RuntimeException("Failed to go back to Home page.", e);
        }
    }

    /**
     * Handles the news button action to show news related to crops and diseases.
     *
     * @param event the action event fired by the news button
     */
    @FXML
    private void showNews(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(FxmlLoader.getFxml("NewsPage"));
            Parent newsRoot = loader.load();

            infoPanel.getChildren().clear();
            infoPanel.getChildren().add(newsRoot);
        } catch (IOException e) {
            throw new FxmlLoaderException("Failed to load NewsPage.fxml", e);
        }
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
     * Handles the account info button action to show user account information.
     *
     * @param event the action event fired by the account info button
     */
    @FXML
    private void showAccountInfo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(FxmlLoader.getFxml("AccountInfoPage"));
            Parent accountInfoRoot = loader.load();

            AccountInfoPageController accountInfoController = loader.getController();
            accountInfoController.setCurrentUser(currentUser);
            accountInfoController.init();

            infoPanel.getChildren().clear();
            infoPanel.getChildren().add(accountInfoRoot);
        } catch (IOException e) {
            throw new FxmlLoaderException("Failed to load AccountInfoPage.fxml", e);
        }
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

    /**
     * Handles the logout button action to navigate to the login page.
     *
     * @param event the action event fired by the logout button
     * @throws IllegalStateException if the primary stage has not been initialized
     */
    @FXML
    private void handleLogout(ActionEvent event) {
        if (stage == null) {
            throw new IllegalStateException("Primary stage has not been initialized.");
        }

        stage.setScene(new LoginPage(stage).createScene());
    }

    /**
     * Handles crop selection via button click.
     *
     * @param event the action event
     */
    @FXML
    private void handleCropSelection(ActionEvent event) {
        String cropName;

        if (event.getSource() instanceof Button button) {
            cropName = button.getText();
        } else {
            cropName =
                    cropSearchField.getText() != null ? cropSearchField.getText() : "Unknown Crop";
        }

        Crop crop = cropCatalogService.findCropByNameOrId(cropName);
        if (crop == null) {
            showUnavailableCropMessage(cropName);
            return;
        }

        showDiseaseIdentification(crop);
    }

    /**
     * Handles crop selection via text field input and search button click.
     *
     * @param event the action event
     */
    @FXML
    private void handleCropSearch(ActionEvent event) {
        String cropName = cropSearchField.getText();

        if (cropName == null || cropName.trim().isEmpty()) {
            cropSearchField.setPromptText("PLEASE ENTER A CROP NAME!");

            if (!cropSearchField.getStyleClass().contains("search-field-error")) {
                cropSearchField.getStyleClass().add("search-field-error");
            }

            return;
        }

        cropSearchField.getStyleClass().remove("search-field-error");
        cropSearchField.setPromptText("Search crop...");

        showSearchResults(cropName.trim());
    }

    /**
     * Hides the header buttons that should disappear during search mode.
     */
    private void hideHomeNavigation() {
        if (newsButton != null) {
            newsButton.setVisible(false);
            newsButton.setManaged(false);
        }

        if (feedsButton != null) {
            feedsButton.setVisible(false);
            feedsButton.setManaged(false);
        }

        if (settingsButton != null) {
            settingsButton.setVisible(false);
            settingsButton.setManaged(false);
        }
    }

    /**
     * Switches the layout into focused mode used by search and disease-identification views.
     */
    private void applyFocusedLayout() {
        if (!searchModeActive) {
            settingsPanelVisibleBeforeSearch = settingsPanel.isVisible();
        }

        searchModeActive = true;
        hideHomeNavigation();

        trendingPanel.setVisible(false);
        trendingPanel.setManaged(false);
        settingsPanel.setVisible(false);
        settingsPanel.setManaged(false);
    }

    /**
     * Switches the page into search mode and renders crop cards matching the query.
     *
     * @param query the user-entered search text
     */
    private void showSearchResults(String query) {
        applyFocusedLayout();

        List<Crop> matches = cropCatalogService.findMatchingCrops(query, MAX_SEARCH_RESULTS);
        VBox searchRoot = buildSearchResultsPanel(query, matches);
        infoPanel.getChildren().setAll(searchRoot);
        StackPane.setAlignment(searchRoot, Pos.TOP_CENTER);
    }

    /**
     * Builds the search-results content shown inside the main info panel.
     *
     * @param query the search query
     * @param matches the matching crop entries
     * @return the root panel for search results
     */
    private VBox buildSearchResultsPanel(String query, List<Crop> matches) {
        VBox root = new VBox(16);
        root.getStyleClass().add("search-results-shell");
        root.setPadding(new Insets(28, 32, 28, 32));
        root.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Searching the Crop");
        title.setMaxWidth(Double.MAX_VALUE);
        title.getStyleClass().add("search-results-title");

        Label subtitle = new Label(
                matches.isEmpty() ? "No strong crop matches were found for \"" + query + "\"."
                        : "Results for \"" + query + "\"");
        subtitle.getStyleClass().add("search-results-subtitle");

        root.getChildren().addAll(title, subtitle);

        if (matches.isEmpty()) {
            Label emptyState =
                    new Label("Nothing found. Try another crop name or a wider search term.");
            emptyState.getStyleClass().add("search-empty-message");
            root.getChildren().add(emptyState);
        } else {
            VBox resultList = new VBox(12);
            resultList.setAlignment(Pos.CENTER);
            resultList.setFillWidth(false);
            resultList.setMaxWidth(SEARCH_RESULT_CARD_WIDTH);

            for (Crop crop : matches) {
                resultList.getChildren().add(createSearchResultCard(crop));
            }

            root.getChildren().add(resultList);
        }

        return root;
    }

    /**
     * Creates a pressable card for a single crop search result.
     *
     * @param crop the crop to represent
     * @return the styled button card
     */
    private Button createSearchResultCard(Crop crop) {
        Label title = new Label(crop.getName());
        title.getStyleClass().add("search-result-title");

        Label details = new Label(buildSearchResultDetails(crop));
        details.getStyleClass().add("search-result-subtitle");
        details.setWrapText(true);

        VBox cardBody = new VBox(4, title, details);
        cardBody.getStyleClass().add("search-result-card-body");
        cardBody.setMaxWidth(SEARCH_RESULT_CARD_WIDTH - 36.0);
        cardBody.setPrefWidth(SEARCH_RESULT_CARD_WIDTH - 36.0);

        Button cardButton = new Button();
        cardButton.getStyleClass().add("search-result-card");
        cardButton.setMaxWidth(SEARCH_RESULT_CARD_WIDTH);
        cardButton.setPrefWidth(SEARCH_RESULT_CARD_WIDTH);
        cardButton.setMinWidth(SEARCH_RESULT_CARD_WIDTH);
        cardButton.setMaxHeight(SEARCH_RESULT_CARD_HEIGHT);
        cardButton.setPrefHeight(SEARCH_RESULT_CARD_HEIGHT);
        cardButton.setMinHeight(SEARCH_RESULT_CARD_HEIGHT);
        cardButton.setAlignment(Pos.CENTER_LEFT);
        cardButton.setGraphic(cardBody);
        cardButton.setMnemonicParsing(false);
        cardButton.setOnAction(event -> {
            showDiseaseIdentification(crop);
        });

        return cardButton;
    }

    /**
     * Opens disease-identification mode for the selected crop.
     *
     * @param crop selected crop metadata
     * @throws NullPointerException if {@code crop} is null
     */
    private void showDiseaseIdentification(Crop crop) {
        selectedCropData = Objects.requireNonNull(crop, "crop");
        applyFocusedLayout();

        VBox identificationRoot = buildDiseaseIdentificationPanel(crop);
        infoPanel.getChildren().setAll(identificationRoot);
        StackPane.setAlignment(identificationRoot, Pos.TOP_CENTER);
    }

    /**
     * Builds the disease-identification content for the selected crop.
     *
     * @param crop selected crop metadata
     * @return disease-identification panel root
     */
    private VBox buildDiseaseIdentificationPanel(Crop crop) {
        VBox root = new VBox(16);
        root.getStyleClass().add("identification-shell");
        root.setPadding(new Insets(28, 32, 28, 32));
        root.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Disease Identification from Symptoms");
        title.getStyleClass().add("identification-title");

        Label subtitle = new Label("Selected crop: " + crop.getName());
        subtitle.getStyleClass().add("identification-subtitle");

        VBox cropInfoCard = new VBox(8);
        cropInfoCard.getStyleClass().add("identification-crop-card");

        Label cropName = new Label(crop.getName());
        cropName.getStyleClass().add("identification-crop-name");

        String scientificName =
                crop.getScientificName() == null || crop.getScientificName().isBlank()
                        ? "Scientific name unavailable"
                        : crop.getScientificName();
        Label cropScientificName = new Label(scientificName);
        cropScientificName.getStyleClass().add("identification-crop-scientific");

        Label cropDescription = new Label(ellipsis(crop.getDescription(), 200));
        cropDescription.getStyleClass().add("identification-crop-description");
        cropDescription.setWrapText(true);

        cropInfoCard.getChildren().addAll(cropName, cropScientificName, cropDescription);

        VBox symptomPickerCard = new VBox(10);
        symptomPickerCard.getStyleClass().add("identification-symptom-card");

        Label symptomTitle = new Label("Describe Symptoms");
        symptomTitle.getStyleClass().add("identification-symptom-title");

        Label symptomHint = new Label(
                "Type a symptom and pick a suggestion from this crop's disease metadata.");
        symptomHint.getStyleClass().add("identification-symptom-hint");
        symptomHint.setWrapText(true);

        ComboBox<String> symptomBox = new ComboBox<>();
        symptomBox.setEditable(true);
        symptomBox.setPromptText("Type symptom keywords...");
        symptomBox.getStyleClass().add("symptom-combo-box");
        symptomBox.setMaxWidth(Double.MAX_VALUE);

        List<String> symptomPool = cropCatalogService.collectSymptoms(crop);
        symptomBox.getItems().setAll(symptomPool);

        symptomBox.getEditor().textProperty().addListener((obs, oldText, newText) -> {
            List<String> filtered = cropCatalogService.filterSymptoms(symptomPool, newText,
                    MAX_SYMPTOM_SUGGESTIONS);
            symptomBox.getItems().setAll(filtered);

            if (!filtered.isEmpty()) {
                symptomBox.show();
            }
        });

        symptomBox.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null || newValue.isBlank()) {
                return;
            }

            showDiseaseResult(selectedCropData, newValue);
        });

        symptomPickerCard.getChildren().addAll(symptomTitle, symptomHint, symptomBox);

        root.getChildren().addAll(title, subtitle, cropInfoCard, symptomPickerCard);
        return root;
    }

    /**
     * Displays disease results for a selected symptom within the current crop.
     *
     * @param crop the selected crop
     * @param symptom the selected symptom
     */
    private void showDiseaseResult(Crop crop, String symptom) {
        if (crop == null || symptom == null || symptom.isBlank()) {
            return;
        }

        List<Disease> matchingDiseases = cropCatalogService.findDiseasesBySymptom(crop, symptom);

        if (matchingDiseases.isEmpty()) {
            Label noDiseaseLabel = new Label(
                    "No diseases found for symptom \"" + symptom + "\" in " + crop.getName());
            noDiseaseLabel.getStyleClass().add("search-empty-message");
            noDiseaseLabel.setWrapText(true);

            VBox noResultsPanel = new VBox(10);
            noResultsPanel.getStyleClass().add("identification-shell");
            noResultsPanel.setPadding(new Insets(28, 32, 28, 32));
            noResultsPanel.setAlignment(Pos.TOP_CENTER);

            Label title = new Label("Disease Identification Results");
            title.getStyleClass().add("identification-title");

            noResultsPanel.getChildren().addAll(title, noDiseaseLabel);
            infoPanel.getChildren().setAll(noResultsPanel);
            return;
        }

        Disease selectedDisease = matchingDiseases.get(0);
        VBox diseaseDetailsPanel = buildDiseaseDetailsPanel(selectedDisease, crop);
        infoPanel.getChildren().setAll(diseaseDetailsPanel);
        StackPane.setAlignment(diseaseDetailsPanel, Pos.TOP_CENTER);
    }

    /**
     * Builds a detailed panel displaying information about a specific disease.
     *
     * @param disease the disease to display
     * @param crop the crop context
     * @return a panel containing disease details
     */
    private VBox buildDiseaseDetailsPanel(Disease disease, Crop crop) {
        VBox root = new VBox(16);
        root.getStyleClass().add("identification-shell");
        root.setPadding(new Insets(28, 32, 28, 32));
        root.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Disease Identification Results");
        title.getStyleClass().add("identification-title");
        root.getChildren().add(title);

        VBox diseaseCard = new VBox(10);
        diseaseCard.getStyleClass().add("disease-crop-card");

        Label diseaseTitle = new Label(disease.getName());
        diseaseTitle.getStyleClass().add("disease-name");
        diseaseCard.getChildren().add(diseaseTitle);

        if (disease.getPathogen() != null && !disease.getPathogen().isBlank()) {
            Label pathogenLabel = new Label("Pathogen: " + disease.getPathogen());
            pathogenLabel.getStyleClass().add("disease-pathogen");
            diseaseCard.getChildren().add(pathogenLabel);
        }

        if (disease.getCategory() != null && !disease.getCategory().isBlank()) {
            Label categoryLabel = new Label("Category: " + (disease.getCategory()));
            categoryLabel.getStyleClass().add("disease-category");
            diseaseCard.getChildren().add(categoryLabel);
        }

        Label descriptionLabel = null;
        if (disease.getDescription() != null && !disease.getDescription().isBlank()) {
            descriptionLabel = new Label(disease.getDescription());
            descriptionLabel.setWrapText(true);
        } else {
            descriptionLabel = new Label("Description not available.");
        }
        descriptionLabel.getStyleClass().add("disease-description");
        diseaseCard.getChildren().add(descriptionLabel);

        ImageView diseaseImage = loadDiseaseImage(disease, crop);
        if (diseaseImage != null) {
            diseaseImage.setFitWidth(400);
            diseaseImage.setFitHeight(300);
            diseaseImage.setPreserveRatio(true);
            diseaseImage.getStyleClass().add("disease-image");

            VBox imageContainer = new VBox(diseaseImage);
            imageContainer.getStyleClass().add("disease-image-container");
            imageContainer.setAlignment(Pos.CENTER);
            root.getChildren().add(imageContainer);
        } else {
            Label noImageLabel = new Label("No disease image available for this crop.");
            noImageLabel.getStyleClass().add("disease-no-image");
            VBox noImageContainer = new VBox(noImageLabel);
            noImageContainer.getStyleClass().add("disease-image-container");
            noImageContainer.setAlignment(Pos.CENTER);
            root.getChildren().add(noImageContainer);
        }

        VBox treatmentCard = new VBox(10);
        treatmentCard.getStyleClass().add("disease-treatment-card");

        Label treatmentTitle = new Label("Treatments / Management");
        treatmentTitle.getStyleClass().add("disease-treatment-title");
        treatmentCard.getChildren().add(treatmentTitle);


        VBox treatmentList = new VBox();
        treatmentList.getStyleClass().add("disease-treatment-list");
        treatmentCard.getChildren().add(treatmentList);

        if (disease.getTreatments() != null && !disease.getTreatments().isEmpty()) {
            for (String treatment : disease.getTreatments()) {
                Label treatmentItem = new Label("• " + treatment);
                treatmentItem.getStyleClass().add("disease-treatment-item");
                treatmentItem.setWrapText(true);
                treatmentList.getChildren().add(treatmentItem);
            }
        } else {
            Label noTreatmentLabel = new Label("No treatments available.");
            noTreatmentLabel.getStyleClass().add("disease-treatment-item");
            treatmentList.getChildren().add(noTreatmentLabel);
        }

        root.getChildren().addAll(diseaseCard, treatmentCard);
        return root;
    }

    /**
     * Attempts to load a disease image from local or remote sources.
     *
     * @param disease the disease whose image to load
     * @param crop the crop context (used to determine local image availability)
     * @return a JavaFX ImageView if successful, null otherwise
     */
    private ImageView loadDiseaseImage(Disease disease, Crop crop) {
        ImageView imageContainer = null;
        String imageUrl = disease.getImage();

        if (imageUrl == null || imageUrl.isBlank()) {
            return null;
        }

        if (imageUrl.startsWith("http")) {
            try {
                Image image = new Image(imageUrl, true);

                if (image != null && !image.isError()) {
                    imageContainer = new ImageView(image);
                    imageContainer.setSmooth(true);
                    imageContainer.setCache(true);
                    return imageContainer;
                }

                return imageContainer;

            } catch (Exception e) {
                System.err.println(new ResourceLoaderException(
                        "Failed to load disease image from URL: " + e.getMessage(), e));
                return null;
            }

        }

        try {
            Image image = ImageLoader.getImageAsImage(crop.getId() + "/" + imageUrl);

            if (image != null && !image.isError()) {
                imageContainer = new ImageView(image);
                imageContainer.setSmooth(true);
                imageContainer.setCache(true);
                return imageContainer;
            }
        } catch (Exception e) {
            System.err.println(new ResourceLoaderException(
                    "Failed to load disease image: " + e.getMessage(), e));
            return null;
        }

        return imageContainer;
    }

    /**
     * Shows a focused-mode message when trending crop metadata is not available.
     *
     * @param cropName selected crop name
     */
    private void showUnavailableCropMessage(String cropName) {
        applyFocusedLayout();

        VBox root = new VBox(14);
        root.getStyleClass().add("identification-shell");
        root.setPadding(new Insets(28, 32, 28, 32));
        root.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Disease Identification from Symptoms");
        title.getStyleClass().add("identification-title");

        Label message = new Label(
                "No metadata found for \"" + cropName + "\" yet. Please choose another crop.");
        message.getStyleClass().add("search-empty-message");
        message.setWrapText(true);

        root.getChildren().addAll(title, message);

        infoPanel.getChildren().setAll(root);
        StackPane.setAlignment(root, Pos.TOP_CENTER);
    }

    /**
     * Builds an ellipsized summary string.
     *
     * @param value source text
     * @param maxLength max output length
     * @return shortened text where needed
     */
    private String ellipsis(String value, int maxLength) {
        if (value == null || value.isBlank()) {
            return "Description not available.";
        }

        String trimmed = value.trim();
        if (trimmed.length() <= maxLength) {
            return trimmed;
        }

        return trimmed.substring(0, maxLength - 3) + "...";
    }

    /**
     * Builds subtitle text for a crop search card.
     *
     * @param crop the crop to describe
     * @return the subtitle text
     */
    private String buildSearchResultDetails(Crop crop) {
        String scientificName =
                crop.getScientificName() == null || crop.getScientificName().isBlank()
                        ? "Scientific name unavailable"
                        : crop.getScientificName();
        int diseaseCount = crop.getDiseases() == null ? 0 : crop.getDiseases().size();
        return scientificName + " • " + diseaseCount + " diseases";
    }
}
