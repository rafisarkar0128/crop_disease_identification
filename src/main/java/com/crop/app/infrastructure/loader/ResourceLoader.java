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

/**
 * Utility for loading application resources from the classpath.
 *
 * <p>
 * Provides helpers for resolving absolute resource paths and reading resources as URLs, external
 * URL strings, or input streams. All public methods validate input and throw
 * {@link ResourceLoaderException} for invalid paths or missing resources.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 14-04-2026
 */
public final class ResourceLoader {
    /**
     * Prevents instantiation of this utility class.
     */
    private ResourceLoader() {}

    /**
     * Returns an absolute classpath path for an application resource.
     *
     * @param path the relative or absolute path to the resource (e.g., "metadata/rice.json" or
     *        "/com/crop/app/metadata/rice.json")
     * @return the absolute path to the resource (e.g., "/com/crop/app/metadata/rice.json" from
     *         resource root)
     * @throws ResourceLoaderException if the provided path is null or blank
     */
    public static String getAbsolutePath(String path) throws ResourceLoaderException {
        if (path == null || path.isBlank()) {
            throw new ResourceLoaderException("Resource path cannot be null or blank.");
        }

        if (path.startsWith("/")) {
            return path;
        }

        return "/" + AppConstants.BASE_PACKAGE_PATH + "/" + path;
    }

    /**
     * Resolves a classpath resource to a URL.
     *
     * @param absolutePath the absolute classpath path to the resource
     * @return the resolved resource URL
     * @throws ResourceLoaderException if the resource cannot be found at the given absolute path
     */
    private static URL resolveResource(String absolutePath) throws ResourceLoaderException {
        var resource = ResourceLoader.class.getResource(absolutePath);

        if (resource == null) {
            throw new ResourceLoaderException("Resource not found at: " + absolutePath
                    + ". Ensure the file exists in src/main/resources" + absolutePath + ".");
        }

        return resource;
    }

    /**
     * Resolves a resource to a URL.
     *
     * @param path the name of the resource (e.g., "logo.png" or "metadata/rice.json").
     * @return the URL of the resource (e.g., URL of resource "background.png")
     * @throws ResourceLoaderException if the resource cannot be found for the resolved path or if
     *         the provided path is null or blank
     */
    public static URL getResource(String path) throws ResourceLoaderException {
        return resolveResource(getAbsolutePath(path));
    }

    /**
     * Resolves a resource to an external URL string.
     *
     * @param path the name of the resource (e.g., "logo.png" or "metadata/rice.json").
     * @return the external form of the resource path (e.g.,
     *         "file:/path/to/resource/images/background.png" for resource "background.png")
     * @throws ResourceLoaderException if the resource cannot be found or if the provided path is
     *         null or blank
     */
    public static String getResourcePath(String path) throws ResourceLoaderException {
        return resolveResource(getAbsolutePath(path)).toExternalForm();
    }

    /**
     * Opens a classpath resource as an input stream.
     *
     * @param path the relative or absolute resource path (e.g., "rice.json" or
     *        "metadata/rice.json")
     * @return an input stream for the specified resource
     * @throws ResourceLoaderException if the resource cannot be found for the resolved path or if
     *         the provided path is null or blank
     */
    public static InputStream getResourceStream(String path) throws ResourceLoaderException {
        String absolutePath = getAbsolutePath(path);
        InputStream inputStream = ResourceLoader.class.getResourceAsStream(absolutePath);

        if (inputStream == null) {
            throw new ResourceLoaderException("Stream could not be opened for: " + absolutePath
                    + ". Ensure the file exists in src/main/resources" + absolutePath + ".");
        }

        return inputStream;
    }
}
