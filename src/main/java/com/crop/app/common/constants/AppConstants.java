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

package com.crop.app.common.constants;

/**
 * Centralized application constants.
 *
 * <p>
 * Contains static metadata fields (name, version, author, license) and classpath-relative resource
 * path constants used by resource loading and UI setup.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 14-04-2026
 */
public final class AppConstants {

    private AppConstants() {
        // Private constructor to prevent instantiation
    }

    public static final String APP_NAME = "Crop Disease Identification";
    public static final String APP_VERSION = "1.0";
    public static final String APP_AUTHOR = "Md. Rafi Sarkar (rafisarkar0128)";
    public static final String APP_COPYRIGHT =
            "Copyright 2026-Present Md. Rafi Sarkar (rafisarkar0128), and contributors.";
    public static final String APP_LICENSE = "Licensed under the Apache License, Version 2.0";
    public static final String APP_LICENSE_URL = "http://www.apache.org/licenses/LICENSE-2.0";
    public static final String APP_DESCRIPTION =
            "A Java application for identifying crop diseases based on metadata.";
    public static final String BASE_PACKAGE_PATH = "com/crop/app";
    public static final String METADATA_PATH = "metadata";
    public static final String METADATA_INDEX = "metadata-index.json";
    public static final String IMAGES_PATH = "images";
    public static final String ICONS_PATH = "icons";
    public static final String FXML_PATH = "fxml";
}
