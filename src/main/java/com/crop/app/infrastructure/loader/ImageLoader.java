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
import com.crop.app.common.constants.ResourceConstants;
import com.crop.app.common.exception.ResourceLoaderException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Utility for loading image resources from the classpath.
 *
 * <p>
 * Provides helpers for resolving image paths and reading image resources as URLs, input streams,
 * and JavaFX image objects. Image identifiers are validated; if no extension is provided, `.png` is
 * used by default.
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
     * Ensures the image identifier is present before path formatting.
     *
     * @param image the image identifier or filename
     * @return the validated image identifier
     * @throws ResourceLoaderException if the image identifier is null or blank
     */
    private static String requireImage(String image) throws ResourceLoaderException {
        if (image == null || image.isBlank()) {
            throw new ResourceLoaderException("Image identifier cannot be null or blank.");
        }

        return image;
    }

    /**
     * Builds an image resource path for the given image.
     *
     * @param image the image identifier or filename. Include an extension for formats other than
     *        PNG (e.g., "background.jpg"). If the extension is missing (e.g., "background"), `.png`
     *        will be appended.
     * @return the formatted image path (e.g., "images/background.png" for image "background.png" or
     *         "background")
     * @throws ResourceLoaderException if the image identifier is null or blank
     */
    private static String format(String image) {
        String validatedImage = requireImage(image);
        String lower = validatedImage.toLowerCase();

        boolean hasExtension =
                lower.endsWith(".png") || lower.endsWith(".jpg") || lower.endsWith(".jpeg");

        String filename = hasExtension ? validatedImage : validatedImage + ".png";
        return ResourceConstants.IMAGES_PATH + "/" + filename;
    }

    /**
     * Builds a relative image path for the given image identifier.
     *
     * @param image the image identifier or filename (e.g., "background.png" or "background"). If no
     *        extension is provided, `.png` will be appended.
     * @return the relative path to the image resource (e.g., "images/background.png" for image
     *         "background.png")
     * @throws ResourceLoaderException if the image identifier is null or blank
     */
    public static String resolveImagePath(String image) {
        return format(image);
    }

    /**
     * Builds an absolute image path for the given image identifier.
     *
     * @param image the image identifier or filename (e.g., "background.png"). Remember to include
     *        the file extension.
     * @return the absolute path to the image resource (e.g., "/com/crop/app/images/background.png"
     *         for image "background.png")
     * @throws ResourceLoaderException if the image identifier is null or blank
     */
    public static String getImagePathAbsolute(String image) {
        return ResourceLoader.getAbsolutePath(format(image));
    }

    /**
     * Resolves an image resource path to a URL.
     *
     * @param image the name of the image resource (e.g., "background.png"). Remember to include the
     *        file extension.
     * @return the URL of the image resource
     * @throws ResourceLoaderException if the image identifier is null or blank, or if the resource
     *         cannot be found
     */
    public static URL getImage(String image) {
        return ResourceLoader.getResource(format(image));
    }

    /**
     * Opens an image resource as an input stream.
     *
     * @param image the name of the image resource (e.g., "background.png"). Remember to include the
     *        file extension.
     * @return an input stream for the specified image resource path
     * @throws ResourceLoaderException if the image identifier is null or blank, or if the resource
     *         stream cannot be opened
     */
    public static InputStream getImageStream(String image) {
        return ResourceLoader.getResourceStream(format(image));
    }

    /**
     * Loads an image resource as a JavaFX Image object.
     *
     * @param image the name of the image resource (e.g., "background.png"). Remember to include the
     *        file extension.
     * @return a JavaFX Image object for the specified image resource path
     * @throws ResourceLoaderException if the image identifier is null or blank, or if the resource
     *         cannot be found
     */
    public static Image getImageAsImage(String image) {
        return new Image(getImage(image).toExternalForm());
    }

    /**
     * Loads an image resource as a JavaFX ImageView object.
     *
     * @param image the name of the image resource (e.g., "background.png"). Remember to include the
     *        file extension.
     * @return a JavaFX ImageView object containing the specified image resource
     * @throws ResourceLoaderException if the image identifier is null or blank, or if the resource
     *         cannot be found
     */
    public static ImageView getImageAsImageView(String image) {
        return new ImageView(getImageAsImage(image));
    }
}
