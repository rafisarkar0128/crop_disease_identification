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

/**
 * Utility for loading metadata resources from the classpath.
 *
 * <p>
 * Provides helpers for resolving absolute metadata paths and reading metadata resources as URLs,
 * input streams, and more.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 17-04-2026
 */
public class MetadataLoader {
    /**
     * Prevents instantiation of this utility class.
     */
    private MetadataLoader() {}

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
     * Builds a relative metadata path for the given crop identifier.
     *
     * @param cropId the crop identifier or metadata filename
     * @return the relative path to the metadata resource (e.g., "metadata/rice.json" for cropId
     *         "rice")
     */
    public static String getMetadataPath(String cropId) {
        return formatMetadataPath(cropId);
    }

    /**
     * Builds an absolute metadata path for the given crop identifier.
     *
     * @param cropId the crop identifier or metadata filename
     * @return the absolute path to the metadata resource (e.g., "/com/crop/app/metadata/rice.json"
     *         for cropId "rice")
     */
    public static String getMetadataPathAbsolute(String cropId) {
        return ResourceLoader.getAbsolutePath(getMetadataPath(cropId));
    }

    /**
     * Resolves metadata for a crop to an external URL string.
     *
     * @param cropId the name of the metadata resource (e.g., "rice"). File extension is optional;
     *        if not provided, ".json" will be appended automatically.
     * @return the external form of the metadata resource path
     */
    public static String getMetadata(String cropId) {
        return ResourceLoader.getResourcePath(formatMetadataPath(cropId));
    }

    /**
     * Opens metadata for a crop as an input stream.
     *
     * @param cropId the name of the metadata resource (e.g., "rice"). File extension is optional;
     *        if not provided, ".json" will be appended automatically.
     * @return an input stream for the specified metadata resource path
     */
    public static InputStream getMetadataStream(String cropId) {
        return ResourceLoader.getResourceStream(formatMetadataPath(cropId));
    }
}
