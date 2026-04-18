package com.crop.app.gui.view;

import java.io.IOException;
import java.util.Objects;
import com.crop.app.gui.controller.SignupPageController;
import com.crop.app.infrastructure.loader.FxmlLoader;
import com.crop.app.infrastructure.loader.StyleLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * View wrapper for creating and applying the sign-up page scene.
 *
 * <p>
 * This class loads the sign-up FXML, injects the primary stage into its controller, and applies the
 * existing sign-up stylesheet for a consistent look.
 */
public class SignupPage {

    private static final int SCENE_WIDTH = 960;
    private static final int SCENE_HEIGHT = 640;

    /**
     * Primary stage where the scene will be displayed.
     */
    private final Stage primaryStage;

    /**
     * Creates a sign-up page view bound to the given stage.
     *
     * @param primaryStage the main application stage
     * @throws NullPointerException if {@code primaryStage} is null
     */
    public SignupPage(Stage primaryStage) {
        this.primaryStage = Objects.requireNonNull(primaryStage, "primaryStage");
    }

    /**
     * Builds and returns the sign-up page scene.
     *
     * @return the fully initialized sign-up page scene
     * @throws IllegalStateException if FXML loading fails
     */
    public Scene createScene() throws IllegalStateException {
        try {
            FXMLLoader loader = new FXMLLoader(FxmlLoader.getFxml("SignupPage"));
            Parent root = loader.load();

            SignupPageController controller = loader.getController();
            controller.setStage(primaryStage);

            Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
            scene.getStylesheets().add(StyleLoader.getStyle("SignupPage").toExternalForm());
            return scene;
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load the sign-up page UI.", exception);
        }
    }

    /**
     * Backward-compatible alias for {@link #createScene()}.
     *
     * @return the sign-up page scene
     * @throws IllegalStateException if FXML loading fails
     */
    public Scene createPage() {
        return createScene();
    }

    /**
     * Creates the sign-up scene and applies it to the primary stage.
     */
    public void setSceneToStage() {
        primaryStage.setScene(createScene());
    }
}
