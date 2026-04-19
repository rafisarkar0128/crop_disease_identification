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
import java.net.URL;
import com.crop.app.common.constants.AppConstants;
import com.crop.app.common.exception.FxmlLoaderException;

/**
 * Utility for loading FXML resources from the classpath.
 *
 * <p>
 * Provides helpers for resolving FXML paths and reading FXML resources as URLs or input streams.
 * FXML identifiers are validated and automatically normalized to a `.fxml` filename when no
 * extension is provided.
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
     * Ensures the FXML identifier is present before path formatting.
     *
     * @param fxmlId the FXML identifier or filename
     * @return the validated FXML identifier
     * @throws FxmlLoaderException if the FXML identifier is null or blank
     */
    private static String requireFxmlId(String fxmlId) throws FxmlLoaderException {
        if (fxmlId == null || fxmlId.isBlank()) {
            throw new FxmlLoaderException("FXML identifier cannot be null or blank.");
        }

        return fxmlId;
    }

    /**
     * Builds an FXML resource path for the given FXML identifier.
     *
     * @param fxmlId the FXML identifier or filename (e.g., "IntroPage.fxml" for fxmlId "IntroPage")
     * @return the formatted FXML path (e.g., "fxml/IntroPage.fxml" for fxmlId "IntroPage")
     * @throws FxmlLoaderException if the FXML identifier is null or blank
     */
    public static String format(String fxmlId) {
        String validatedFxmlId = requireFxmlId(fxmlId);
        String filename =
                validatedFxmlId.endsWith(".fxml") ? validatedFxmlId : validatedFxmlId + ".fxml";
        return AppConstants.FXML_PATH + "/" + filename;
    }

    /**
     * Builds a relative FXML path for the given FXML identifier.
     *
     * @param fxml the FXML identifier or filename (e.g., "IntroPage.fxml" for fxmlId "IntroPage")
     * @return the relative path to the FXML resource (e.g., "fxml/IntroPage.fxml" for fxml
     *         "IntroPage")
     * @throws FxmlLoaderException if the FXML identifier is null or blank
     */
    public static String resolveFxmlPath(String fxml) {
        return format(fxml);
    }

    /**
     * Builds an absolute FXML path for the given FXML identifier.
     *
     * @param fxml the FXML identifier or filename (e.g., "IntroPage.fxml" for fxmlId "IntroPage")
     * @return the absolute path to the FXML resource (e.g., "/com/crop/app/fxml/IntroPage.fxml" for
     *         fxml "IntroPage")
     * @throws FxmlLoaderException if the FXML identifier is null or blank
     */
    public static String getFxmlPathAbsolute(String fxml) {
        return ResourceLoader.getAbsolutePath(format(fxml));
    }

    /**
     * Resolves an FXML resource path to a URL.
     *
     * @param fxml the name of the FXML resource (e.g., "IntroPage.fxml" or "IntroPage").
     * @return the URL of the FXML resource
     * @throws FxmlLoaderException if the FXML identifier is null or blank, or if the resource
     *         cannot be found
     */
    public static URL getFxml(String fxml) {
        return ResourceLoader.getResource(format(fxml));
    }

    /**
     * Opens an FXML resource as an input stream.
     *
     * @param fxml the name of the FXML resource (e.g., "IntroPage.fxml" or "IntroPage").
     * @return an input stream for the specified FXML resource path
     * @throws FxmlLoaderException if the FXML identifier is null or blank, or if the resource
     *         stream cannot be opened
     */
    public static InputStream getFxmlStream(String fxml) {
        return ResourceLoader.getResourceStream(format(fxml));
    }
}
