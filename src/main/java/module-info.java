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


/**
 * Defines module dependencies and package visibility for the Crop Disease Identification
 * application.
 *
 * <p>
 * The module requires JavaFX for the UI and Gson for JSON deserialization. It opens the domain
 * model package to Gson for reflective field access and opens the main package to JavaFX FXML. It
 * exports the application entry package and the domain model package.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 14-04-2026
 */
module crop.disease.identification {
    // JavaFX modules
    requires javafx.graphics;
    requires transitive javafx.controls;
    requires javafx.fxml;

    // Gson module
    requires com.google.gson;

    // Opened for runtime reflection
    opens com.crop.app.domain.model to com.google.gson;
    opens com.crop.app to javafx.fxml;
    opens com.crop.app.gui.controller to javafx.fxml;

    // Exported API packages
    exports com.crop.app;
    exports com.crop.app.domain.model;
}
