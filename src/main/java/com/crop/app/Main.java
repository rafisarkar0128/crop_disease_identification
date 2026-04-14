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

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.io.IOException;
import com.crop.app.domain.model.Crop;
import com.crop.app.domain.model.Disease;
import com.crop.app.infrastructure.loader.ResourceLoader;
import com.crop.app.infrastructure.mapper.CropMetadataMapper;

/**
 * Main class serves as the entry point for the Crop Disease Identification application. It extends
 * the JavaFX Application class to create a GUI application. The main method launches the
 * application and also includes a test case that demonstrates how to use the CropMetadataMapper to
 * load crop data from a JSON file and print the crop name and its associated diseases and symptoms
 * to the console. The start method sets up the primary stage of the JavaFX application, including
 * the scene and its properties such as title and icon.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 14-04-2026
 */
public class Main extends Application {

    /**
     * The main method serves as the entry point for the application. It launches the JavaFX
     * application and includes a test case that demonstrates how to use the CropMetadataMapper to
     * load crop data from a JSON file and print the crop name and its associated diseases and
     * symptoms to the console.
     *
     * @param args the command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        launch();
        testCropMetadataMapper();
    }

    /**
     * The start method is called when the JavaFX application is launched. It sets up the primary
     * stage of the application, including the scene and its properties such as title and icon.
     *
     * @param primaryStage the primary stage of the application
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene scene = new Scene(new Group(), 640, 480, Color.LIMEGREEN);

        primaryStage.setTitle("Crop Identification System");
        primaryStage.getIcons().add(new Image(ResourceLoader.getIcon("logo.png")));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void testCropMetadataMapper() {
        Crop crop = CropMetadataMapper.mapFromJson("jute.json");
        System.out.println("Crop Name: " + crop.getName());
        System.out.println("Diseases:");
        for (Disease disease : crop.getDiseases()) {
            System.out.println("- " + disease.getName());
            System.out.println("  Symptoms:");
            for (String symptom : disease.getSymptoms()) {
                System.out.println("    * " + symptom);
            }
        }

    }
}
