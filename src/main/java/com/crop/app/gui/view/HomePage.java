package com.crop.app.gui.view;

import java.io.IOException;
import java.util.Objects;
import com.crop.app.common.constants.SceneConstants;
import com.crop.app.domain.model.User;
import com.crop.app.gui.controller.HomePageController;
import com.crop.app.infrastructure.loader.FxmlLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * View wrapper for creating and applying the home page scene.
 */
public class HomePage {
    /**
     * Primary stage where the scene will be displayed.
     */
    private final Stage primaryStage;

    /**
     * Primary stage where the scene will be displayed.
     */
    private final User currentUser;

    /**
     * Creates a home page view bound to the given stage.
     *
     * @param primaryStage the main application stage
     * @param currentUser the currently authenticated user
     * @throws NullPointerException if {@code primaryStage} is null
     */
    public HomePage(Stage primaryStage, User currentUser) {
        this.primaryStage = Objects.requireNonNull(primaryStage, "primaryStage");
        this.currentUser = Objects.requireNonNull(currentUser, "currentUser");
    }

    /**
     * Builds and returns the home page scene.
     *
     * @return the fully initialized home page scene
     * @throws IllegalStateException if FXML loading fails
     */
    public Scene createScene() {
        try {
            FXMLLoader loader = new FXMLLoader(FxmlLoader.getFxml("HomePage"));
            Parent root = loader.load();

            HomePageController controller = loader.getController();
            controller.setStage(primaryStage);
            controller.setCurrentUser(currentUser);

            Scene scene =
                    new Scene(root, SceneConstants.DEFAULT_WIDTH, SceneConstants.DEFAULT_HEIGHT);
            return scene;
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load the home page UI.", exception);
        }
    }

    /**
     * Backward-compatible alias for {@link #createScene()}.
     *
     * @return the home page scene
     */
    public Scene createPage() {
        return createScene();
    }

    /**
     * Creates the home scene and applies it to the primary stage.
     */
    public void setSceneToStage() {
        primaryStage.setScene(createScene());
    }
}
