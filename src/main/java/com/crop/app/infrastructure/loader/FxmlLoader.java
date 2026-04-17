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

package com.crop.app.infrastructure.loader;

import java.io.InputStream;
import com.crop.app.common.constants.AppConstants;

/**
 * Utility for loading FXML resources from the classpath.
 *
 * <p>
 * Provides helpers for resolving absolute FXML paths and reading FXML resources as URLs, input
 * streams, and more.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 17-04-2026
 */
public class FxmlLoader {
    /**
     * Prevents instantiation of this utility class.
     */
    private FxmlLoader() {}

    /**
     * Builds an FXML resource path for the given FXML identifier.
     *
     * @param fxmlId the FXML identifier or filename (e.g., "IntroPage.fxml" for fxmlId "IntroPage")
     * @return the formatted FXML path (e.g., "fxml/IntroPage.fxml" for fxmlId "IntroPage")
     */
    public static String formatFxmlPath(String fxmlId) {
        String filename = fxmlId.endsWith(".fxml") ? fxmlId : fxmlId + ".fxml";
        return AppConstants.FXML_PATH + "/" + filename;
    }

    /**
     * Builds a relative FXML path for the given FXML identifier.
     *
     * @param fxml the FXML identifier or filename (e.g., "IntroPage.fxml" for fxmlId "IntroPage")
     * @return the relative path to the FXML resource (e.g., "fxml/IntroPage.fxml" for fxml
     *         "IntroPage")
     */
    public static String getFxmlPath(String fxml) {
        return formatFxmlPath(fxml);
    }

    /**
     * Builds an absolute FXML path for the given FXML identifier.
     *
     * @param fxml the FXML identifier or filename (e.g., "IntroPage.fxml" for fxmlId "IntroPage")
     * @return the absolute path to the FXML resource (e.g., "/com/crop/app/fxml/IntroPage.fxml" for
     *         fxml "IntroPage")
     */
    public static String getFxmlPathAbsolute(String fxml) {
        return ResourceLoader.getAbsolutePath(getFxmlPath(fxml));
    }

    /**
     * Resolves an FXML resource path to an external URL string.
     *
     * @param fxml the name of the FXML resource (e.g., "IntroPage.fxml" or "IntroPage").
     * @return the external form of the FXML resource path
     */
    public static String getFxml(String fxml) {
        return ResourceLoader.getResourcePath(getFxmlPath(fxml));
    }

    /**
     * Opens an FXML resource as an input stream.
     *
     * @param fxml the name of the FXML resource (e.g., "IntroPage.fxml" or "IntroPage").
     * @return an input stream for the specified FXML resource path
     */
    public static InputStream getFxmlStream(String fxml) {
        return ResourceLoader.getResourceStream(getFxmlPath(fxml));
    }
}
