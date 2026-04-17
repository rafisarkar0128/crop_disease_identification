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
 * Utility for loading image resources from the classpath.
 *
 * <p>
 * Provides helpers for resolving absolute image paths and reading image resources as URLs, input
 * streams, and more.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 17-04-2026
 */
public class ImageLoader {
    /**
     * Prevents instantiation of this utility class.
     */
    private ImageLoader() {}

    /**
     * Builds a relative image path for the given image identifier.
     *
     * @param image the image identifier or filename (e.g., "background.png" for imageId
     *        "background"). Remember to include the file extension.
     * @return the relative path to the image resource (e.g., "images/background.png" for image
     *         "background.png")
     */
    public static String getImagePath(String image) {
        return AppConstants.IMAGES_PATH + "/" + image;
    }

    /**
     * Builds an absolute image path for the given image identifier.
     *
     * @param image the image identifier or filename (e.g., "background.png"). Remember to include
     *        the file extension.
     * @return the absolute path to the image resource (e.g., "/com/crop/app/images/background.png"
     *         for image "background.png")
     */
    public static String getImagePathAbsolute(String image) {
        return ResourceLoader.getAbsolutePath(getImagePath(image));
    }

    /**
     * Resolves an image resource path to an external URL string.
     *
     * @param image the name of the image resource (e.g., "background.png"). Remember to include the
     *        file extension.
     * @return the external form of the image resource path
     */
    public static String getImage(String image) {
        return ResourceLoader.getResourcePath(getImagePath(image));
    }

    /**
     * Opens an image resource as an input stream.
     *
     * @param image the name of the image resource (e.g., "background.png"). Remember to include the
     *        file extension.
     * @return an input stream for the specified image resource path
     */
    public static InputStream getImageStream(String image) {
        return ResourceLoader.getResourceStream(getImagePath(image));
    }

    /**
     * Loads an image resource as a JavaFX Image object.
     *
     * @param image the name of the image resource (e.g., "background.png"). Remember to include the
     *        file extension.
     * @return a JavaFX Image object for the specified image resource path
     */
    public static Image getImageAsImage(String image) {
        return new Image(getImage(image));
    }

    /**
     * Loads an image resource as a JavaFX ImageView object.
     *
     * @param image the name of the image resource (e.g., "background.png"). Remember to include the
     *        file extension.
     * @return a JavaFX ImageView object containing the specified image resource
     */
    public static ImageView getImageAsImageView(String image) {
        return new ImageView(getImageAsImage(image));
    }
}
