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
import com.crop.app.Main;
import com.crop.app.common.exception.ResourceLoaderException;
import com.crop.app.common.constants.AppConstants;

/**
 * ResourceLoader is responsible for loading resources such as images, models, and other assets
 * required by the application. It provides methods to load resources from various sources, such as
 * the file system, classpath, or external URLs. This class abstracts the resource loading
 * mechanism, allowing for flexibility and ease of maintenance.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 14-04-2026
 */
public class ResourceLoader {
    /**
     * Gets the absolute path for a given path. This method checks if the provided path is already
     * absolute (starts with "/"). If it is not, it prepends the base path "/com/crop/app/" to
     * construct the absolute path. This ensures that all resource paths are correctly resolved
     * relative to the application's classpath. The method returns the resulting absolute path.
     *
     * @param path the relative or absolute path to the resource (e.g., "metadata/rice.json" or
     *        "/com/crop/app/metadata/rice.json")
     * @return the absolute path to the resource
     */
    public static String getAbsolutePath(String path) {
        return path.startsWith("/") ? path : "/com/crop/app/" + path;
    }

    /**
     * Formats the metadata path for a given crop ID. This method appends ".json" to the provided
     * crop ID and returns the resulting path.
     *
     * @param cropId the ID of the crop for which to format the metadata path
     * @return the formatted metadata path (e.g., "metadata/rice.json" for cropId "rice")
     */
    private static String formatMetadataPath(String cropId) {
        String filename = cropId.endsWith(".json") ? cropId : cropId + ".json";
        return AppConstants.METADATA_PATH + "/" + filename;
    }

    /**
     * Gets the resource path for a given resource name. This method constructs the path to the
     * resource based on the classpath and the specified resource name. It returns the external form
     * of the constructed path.
     *
     * @param path the name of the resource (e.g., "icons/logo.png" or "/metadata/rice.png")
     * @return the external form of the resource path
     * @throws ResourceLoaderException if the resource cannot be found at the constructed path
     */
    public static String getResourcePath(String path) {
        String absolutePath = getAbsolutePath(path);
        var resource = Main.class.getResource(absolutePath);

        if (resource == null) {
            throw new ResourceLoaderException("Resource not found at: " + absolutePath
                    + ". Ensure the file exists in src/main/resources" + absolutePath);
        }
        return resource.toExternalForm();
    }

    /**
     * Gets an InputStream for a given resource path. This method attempts to open an InputStream
     * for the specified resource path. If the resource cannot be found or the stream cannot be
     * opened, it throws a ResourceLoaderException with a descriptive error message.
     *
     * @param path The relative path from the app root (e.g., "metadata/rice.json")
     * @return An InputStream for the specified resource path
     * @throws ResourceLoaderException if the resource cannot be found
     */
    public static InputStream getResourceStream(String path) {
        String absolutePath = getAbsolutePath(path);
        InputStream is = Main.class.getResourceAsStream(absolutePath);

        if (is == null) {
            throw new ResourceLoaderException("Stream could not be opened for: " + absolutePath
                    + ". Ensure the file exists in src/main/resources" + absolutePath);
        }
        return is;
    }

    /**
     * Gets the resource path for an icon. This method constructs the path to the icon resource
     * based on the classpath and the specified icon name. It returns the external form of the
     * constructed path.
     *
     * @param icon the name of the icon resource (e.g., "logo.png"). Remember to include the file
     *        extension.
     * @return the external form of the icon resource path
     */
    public static String getIcon(String icon) {
        return getResourcePath(AppConstants.ICONS_PATH + "/" + icon);
    }

    /**
     * Gets an InputStream for an icon. This method attempts to open an InputStream for the
     * specified icon resource path. If the resource cannot be found or the stream cannot be opened,
     * it throws a ResourceLoaderException with a descriptive error message.
     *
     * @param icon the name of the icon resource (e.g., "logo.png"). Remember to include the file
     *        extension.
     * @return An InputStream for the specified icon resource path
     */
    public static InputStream getIconStream(String icon) {
        return getResourceStream(AppConstants.ICONS_PATH + "/" + icon);
    }

    /**
     * Gets the resource path for metadata. This method constructs the path to the metadata resource
     * based on the classpath and the specified metadata name. It returns the external form of the
     * constructed path.
     *
     * @param cropId the name of the metadata resource (e.g., "rice"). File extension is optional;
     *        if not provided, ".json" will be appended automatically.
     * @return the external form of the metadata resource path
     */
    public static String getMetadata(String cropId) {
        return getResourcePath(formatMetadataPath(cropId));
    }

    /**
     * Gets an InputStream for metadata. This method attempts to open an InputStream for the
     * specifiedmetadata resource path. If the resource cannot be found or the stream cannot be
     * opened, it throws a ResourceLoaderException with a descriptive error message.
     *
     * @param cropId the name of the metadata resource (e.g., "rice"). File extension is optional;
     *        if not provided, ".json" will be appended automatically.
     * @return An InputStream for the specified metadata resource path
     */
    public static InputStream getMetadataStream(String cropId) {
        return getResourceStream(formatMetadataPath(cropId));
    }
}
