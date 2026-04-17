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
 * Provides helpers for resolving absolute resource paths and reading resources as URLs or input
 * streams.
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
     * @return the absolute path to the resource
     */
    public static String getAbsolutePath(String path) {
        if (path.startsWith("/")) {
            return path;
        }
        return "/" + AppConstants.BASE_PACKAGE_PATH + "/" + path;
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
}
