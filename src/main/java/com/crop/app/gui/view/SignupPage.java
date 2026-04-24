package com.crop.app.gui.view;

import java.io.IOException;
import java.util.Objects;
import com.crop.app.common.constants.SceneConstants;
import com.crop.app.common.exception.FxmlLoaderException;
import com.crop.app.gui.controller.SignupPageController;
import com.crop.app.infrastructure.loader.FxmlLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SignupPage {

    private final Stage primaryStage;

    public SignupPage(Stage primaryStage) {
        this.primaryStage = Objects.requireNonNull(primaryStage, "primaryStage");
    }

    public Scene createScene() throws FxmlLoaderException {
        try {
            FXMLLoader loader = new FXMLLoader(FxmlLoader.getFxml("SignupPage"));
            Parent root = loader.load();

            SignupPageController controller = loader.getController();
            controller.setStage(primaryStage);

            Scene scene =
                    new Scene(root, SceneConstants.DEFAULT_WIDTH, SceneConstants.DEFAULT_HEIGHT);
            return scene;
        } catch (IOException exception) {
            throw new FxmlLoaderException("Unable to load the sign-up page UI.", exception);
        }
    }

    public Scene createPage() throws FxmlLoaderException {
        return createScene();
    }

    public void setSceneToStage() {
        primaryStage.setScene(createScene());
    }
}
