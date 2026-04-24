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

public class HomePage {
    private final Stage primaryStage;

    private final User currentUser;

    public HomePage(Stage primaryStage, User currentUser) {
        this.primaryStage = Objects.requireNonNull(primaryStage, "primaryStage");
        this.currentUser = Objects.requireNonNull(currentUser, "currentUser");
    }

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

    public Scene createPage() {
        return createScene();
    }

    public void setSceneToStage() {
        primaryStage.setScene(createScene());
    }
}
