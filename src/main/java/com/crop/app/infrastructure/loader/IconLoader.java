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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Utility for loading icon resources from the classpath.
 *
 * <p>
 * Provides helpers for resolving absolute icon paths and reading icon resources as URLs, input
 * streams, and more.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 17-04-2026
 */
public class IconLoader {
    /**
     * Prevents instantiation of this utility class.
     */
    private IconLoader() {}

    /**
     * Builds a relative icon path for the given icon identifier.
     *
     * @param icon the icon identifier or filename (e.g., "logo.png" for iconId "logo"). Remember to
     *        include the file extension.
     * @return the relative path to the icon resource (e.g., "icons/logo.png" for icon "logo.png")
     */
    public static String getIconPath(String icon) {
        return AppConstants.ICONS_PATH + "/" + icon;
    }

    /**
     * Builds an absolute icon path for the given icon identifier.
     *
     * @param icon the icon identifier or filename (e.g., "logo.png"). Remember to include the file
     *        extension.
     * @return the absolute path to the icon resource (e.g., "/com/crop/app/icons/logo.png" for icon
     *         "logo.png")
     */
    public static String getIconPathAbsolute(String icon) {
        return ResourceLoader.getAbsolutePath(getIconPath(icon));
    }

    /**
     * Resolves an icon resource path to an external URL string.
     *
     * @param icon the name of the icon resource (e.g., "logo.png"). Remember to include the file
     *        extension.
     * @return the external form of the icon resource path
     */
    public static String getIcon(String icon) {
        return ResourceLoader.getResourcePath(getIconPath(icon));
    }

    /**
     * Opens an icon resource as an input stream.
     *
     * @param icon the name of the icon resource (e.g., "logo.png"). Remember to include the file
     *        extension.
     * @return an input stream for the specified icon resource path
     */
    public static InputStream getIconStream(String icon) {
        return ResourceLoader.getResourceStream(getIconPath(icon));
    }

    /**
     * Loads an icon resource as a JavaFX Image object.
     *
     * @param icon the name of the icon resource (e.g., "logo.png"). Remember to include the file
     *        extension.
     * @return a JavaFX Image object for the specified icon resource path
     */
    public static Image getIconAsImage(String icon) {
        return new Image(getIcon(icon));
    }

    /**
     * Loads an icon resource as a JavaFX ImageView object.
     *
     * @param icon the name of the icon resource (e.g., "logo.png"). Remember to include the file
     *        extension.
     * @return a JavaFX ImageView object containing the specified icon resource
     */
    public static ImageView getIconAsImageView(String icon) {
        return new ImageView(getIconAsImage(icon));
    }
}
