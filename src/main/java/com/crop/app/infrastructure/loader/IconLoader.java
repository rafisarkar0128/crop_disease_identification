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
import com.crop.app.common.exception.ResourceLoaderException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Utility for loading icon resources from the classpath.
 *
 * <p>
 * Provides helpers for resolving icon paths and reading icon resources as URLs, input streams,
 * and JavaFX image objects. Icon identifiers are validated; if no extension is provided, `.png`
 * is used by default.
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
     * Ensures the icon identifier is present before path formatting.
     *
     * @param icon the icon identifier or filename
     * @return the validated icon identifier
     * @throws ResourceLoaderException if the icon identifier is null or blank
     */
    private static String requireIcon(String icon) throws ResourceLoaderException {
        if (icon == null || icon.isBlank()) {
            throw new ResourceLoaderException("Icon identifier cannot be null or blank.");
        }

        return icon;
    }

    /**
     * Builds a relative icon path for the given icon identifier.
     *
    * @param icon the icon identifier or filename. Include an extension for formats other than PNG
    *        (e.g., "logo.jpg"). If the extension is missing (e.g., "logo"), `.png` will be
    *        appended.
     * @return the relative path to the icon resource (e.g., "icons/logo.png" for icon "logo.png" or
     *         "logo")
    * @throws ResourceLoaderException if the icon identifier is null or blank
     */
    private static String format(String icon) {
        String validatedIcon = requireIcon(icon);
        String lower = validatedIcon.toLowerCase();

        boolean hasExtension =
                lower.endsWith(".png") || lower.endsWith(".jpg") || lower.endsWith(".jpeg");

        String filename = hasExtension ? validatedIcon : validatedIcon + ".png";
        return AppConstants.ICONS_PATH + "/" + filename;
    }

    /**
     * Builds a relative icon path for the given icon identifier.
     *
    * @param icon the icon identifier or filename (e.g., "logo.png" or "logo"). If no extension is
    *        provided, `.png` will be appended.
     * @return the relative path to the icon resource (e.g., "icons/logo.png" for icon "logo.png")
    * @throws ResourceLoaderException if the icon identifier is null or blank
     */
    public static String resolveIconPath(String icon) {
        return format(icon);
    }

    /**
     * Builds an absolute icon path for the given icon identifier.
     *
     * @param icon the icon identifier or filename (e.g., "logo.png"). Remember to include the file
     *        extension.
     * @return the absolute path to the icon resource (e.g., "/com/crop/app/icons/logo.png" for icon
     *         "logo.png")
    * @throws ResourceLoaderException if the icon identifier is null or blank
     */
    public static String getIconPathAbsolute(String icon) {
        return ResourceLoader.getAbsolutePath(format(icon));
    }

    /**
    * Resolves an icon resource path to a URL.
     *
     * @param icon the name of the icon resource (e.g., "logo.png"). Remember to include the file
     *        extension.
    * @return the URL of the icon resource
    * @throws ResourceLoaderException if the icon identifier is null or blank, or if the resource
    *         cannot be found
     */
    public static URL getIcon(String icon) {
        return ResourceLoader.getResource(format(icon));
    }

    /**
     * Opens an icon resource as an input stream.
     *
     * @param icon the name of the icon resource (e.g., "logo.png"). Remember to include the file
     *        extension.
     * @return an input stream for the specified icon resource path
    * @throws ResourceLoaderException if the icon identifier is null or blank, or if the resource
    *         stream cannot be opened
     */
    public static InputStream getIconStream(String icon) {
        return ResourceLoader.getResourceStream(format(icon));
    }

    /**
     * Loads an icon resource as a JavaFX Image object.
     *
     * @param icon the name of the icon resource (e.g., "logo.png"). Remember to include the file
     *        extension.
     * @return a JavaFX Image object for the specified icon resource path
    * @throws ResourceLoaderException if the icon identifier is null or blank, or if the resource
    *         cannot be found
     */
    public static Image getIconAsImage(String icon) {
        return new Image(getIcon(icon).toExternalForm());
    }

    /**
     * Loads an icon resource as a JavaFX ImageView object.
     *
     * @param icon the name of the icon resource (e.g., "logo.png"). Remember to include the file
     *        extension.
     * @return a JavaFX ImageView object containing the specified icon resource
     * @throws ResourceLoaderException if the icon identifier is null or blank, or if the resource
     *         cannot be found
     */
    public static ImageView getIconAsImageView(String icon) {
        return new ImageView(getIconAsImage(icon));
    }
}
