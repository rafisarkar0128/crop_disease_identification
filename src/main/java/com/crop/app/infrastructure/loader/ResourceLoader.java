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
import com.crop.app.common.exception.ResourceLoaderException;

/**
 * Utility for loading application resources from the classpath.
 *
 * <p>
 * Provides helpers for resolving absolute classpath resource paths and reading icon/metadata
 * resources as URLs or input streams.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 14-04-2026
 */
public final class ResourceLoader {
    /**
     * Prevents instantiation of this utility class.
     */
    private ResourceLoader() {
        // Utility class
    }

    /**
     * Returns an absolute classpath path for an application resource.
     *
     * @param path the relative or absolute path to the resource (e.g., "metadata/rice.json" or
     *        "/com/crop/app/metadata/rice.json")
     * @return the absolute path to the resource
     */
    public static String getAbsolutePath(String path) {
        if (path.startsWith("/")) {
            return path;
        }
        return "/" + AppConstants.BASE_PACKAGE_PATH + "/" + path;
    }

    /**
     * Builds a metadata resource path for the given crop identifier.
     *
     * @param cropId the crop identifier or metadata filename
     * @return the formatted metadata path (e.g., "metadata/rice.json" for cropId "rice")
     */
    public static String formatMetadataPath(String cropId) {
        String filename = cropId.endsWith(".json") ? cropId : cropId + ".json";
        return AppConstants.METADATA_PATH + "/" + filename;
    }

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
     * Resolves a classpath resource to an external URL string.
     *
     * @param path the relative or absolute resource path
     * @return the external form of the resource path
     * @throws ResourceLoaderException if the resource cannot be found at the constructed path
     */
    public static String getResourcePath(String path) {
        String absolutePath = getAbsolutePath(path);
        var resource = ResourceLoader.class.getResource(absolutePath);

        if (resource == null) {
            throw new ResourceLoaderException("Resource not found at: " + absolutePath
                    + ". Ensure the file exists in src/main/resources" + absolutePath);
        }
        return resource.toExternalForm();
    }

    /**
     * Opens a classpath resource as an input stream.
     *
     * @param path the relative or absolute resource path
     * @return an input stream for the specified resource
     * @throws ResourceLoaderException if the resource cannot be found
     */
    public static InputStream getResourceStream(String path) {
        String absolutePath = getAbsolutePath(path);
        InputStream is = ResourceLoader.class.getResourceAsStream(absolutePath);

        if (is == null) {
            throw new ResourceLoaderException("Stream could not be opened for: " + absolutePath
                    + ". Ensure the file exists in src/main/resources" + absolutePath);
        }
        return is;
    }

    /**
     * Resolves an FXML resource path to an external URL string.
     *
     * @param fxml the name of the FXML resource (e.g., "IntroPage.fxml" or "IntroPage").
     * @return the external form of the FXML resource path
     */
    public static String getFxml(String fxml) {
        return getResourcePath(formatFxmlPath(fxml));
    }

    /**
     * Opens an FXML resource as an input stream.
     *
     * @param fxml the name of the FXML resource (e.g., "IntroPage.fxml" or "IntroPage").
     * @return an input stream for the specified FXML resource path
     */
    public static InputStream getFxmlStream(String fxml) {
        return getResourceStream(formatFxmlPath(fxml));
    }

    /**
     * Resolves an icon resource path to an external URL string.
     *
     * @param icon the name of the icon resource (e.g., "logo.png"). Remember to include the file
     *        extension.
     * @return the external form of the icon resource path
     */
    public static String getIcon(String icon) {
        return getResourcePath(AppConstants.ICONS_PATH + "/" + icon);
    }

    /**
     * Opens an icon resource as an input stream.
     *
     * @param icon the name of the icon resource (e.g., "logo.png"). Remember to include the file
     *        extension.
     * @return an input stream for the specified icon resource path
     */
    public static InputStream getIconStream(String icon) {
        return getResourceStream(AppConstants.ICONS_PATH + "/" + icon);
    }


    /**
     * Resolves an image resource path to an external URL string.
     *
     * @param image the name of the image resource (e.g., "background.png"). Remember to include the
     *        file extension.
     * @return the external form of the image resource path
     */
    public static String getImage(String image) {
        return getResourcePath(AppConstants.IMAGES_PATH + "/" + image);
    }

    /**
     * Opens an image resource as an input stream.
     *
     * @param image the name of the image resource (e.g., "background.png"). Remember to include the
     *        file extension.
     * @return an input stream for the specified image resource path
     */
    public static InputStream getImageStream(String image) {
        return getResourceStream(AppConstants.IMAGES_PATH + "/" + image);
    }

    /**
     * Resolves metadata for a crop to an external URL string.
     *
     * @param cropId the name of the metadata resource (e.g., "rice"). File extension is optional;
     *        if not provided, ".json" will be appended automatically.
     * @return the external form of the metadata resource path
     */
    public static String getMetadata(String cropId) {
        return getResourcePath(formatMetadataPath(cropId));
    }

    /**
     * Opens metadata for a crop as an input stream.
     *
     * @param cropId the name of the metadata resource (e.g., "rice"). File extension is optional;
     *        if not provided, ".json" will be appended automatically.
     * @return an input stream for the specified metadata resource path
     */
    public static InputStream getMetadataStream(String cropId) {
        return getResourceStream(formatMetadataPath(cropId));
    }
}
