package com.crop.app.gui.view;

import java.io.IOException;
import java.util.Objects;
import com.crop.app.common.constants.SceneConstants;
import com.crop.app.common.exception.FxmlLoaderException;
import com.crop.app.gui.controller.LoginPageController;
import com.crop.app.infrastructure.loader.FxmlLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginPage {

    private final Stage primaryStage;

    public LoginPage(Stage primaryStage) {
        this.primaryStage = Objects.requireNonNull(primaryStage, "primaryStage");
    }

    public Scene createScene() throws FxmlLoaderException {
        try {
            FXMLLoader loader = new FXMLLoader(FxmlLoader.getFxml("LoginPage"));
            Parent root = loader.load();

            LoginPageController controller = loader.getController();
            controller.setStage(primaryStage);

            Scene scene =
                    new Scene(root, SceneConstants.DEFAULT_WIDTH, SceneConstants.DEFAULT_HEIGHT);
            return scene;
        } catch (IOException exception) {
            throw new FxmlLoaderException("Unable to load the login page UI.", exception);
        }
    }

    public Scene createPage() throws FxmlLoaderException {
        return createScene();
    }

    public void setSceneToStage() {
        primaryStage.setScene(createScene());
    }
}
