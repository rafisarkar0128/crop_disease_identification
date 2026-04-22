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

/**
 * View wrapper for creating and applying the sign-up page scene.
 *
 * <p>
 * This class loads the sign-up FXML, injects the primary stage into its controller, and applies the
 * existing sign-up stylesheet for a consistent look.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 19-04-2026
 */
public class SignupPage {
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
     * @throws FxmlLoaderException if FXML loading fails
     */
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

    /**
     * Backward-compatible alias for {@link #createScene()}.
     *
     * @return the sign-up page scene
     * @throws FxmlLoaderException if FXML loading fails
     */
    public Scene createPage() throws FxmlLoaderException {
        return createScene();
    }

    /**
     * Creates the sign-up scene and applies it to the primary stage.
     */
    public void setSceneToStage() {
        primaryStage.setScene(createScene());
    }
}
