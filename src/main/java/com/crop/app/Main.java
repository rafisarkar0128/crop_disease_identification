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

package com.crop.app;

import com.crop.app.common.constants.AppConstants;
import com.crop.app.infrastructure.loader.ResourceLoader;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * JavaFX entry point for the Crop Disease Identification application.
 *
 * <p>
 * This class launches the JavaFX runtime and initializes the primary stage with a basic scene,
 * application title, and window icon loaded from bundled resources.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 14-04-2026
 */
public class Main extends Application {

    /**
     * Launches the JavaFX application runtime.
     *
     * @param args the command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes and shows the primary JavaFX stage.
     *
     * @param primaryStage the primary stage of the application
     */
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new Group(), 640, 480, Color.LIMEGREEN);

        primaryStage.setTitle(AppConstants.APP_NAME + " - " + AppConstants.APP_VERSION);
        primaryStage.getIcons().add(new Image(ResourceLoader.getIcon("logo.png")));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
