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

/**
 * Utility for loading metadata resources from the classpath.
 *
 * <p>
 * Provides helpers for resolving metadata paths and reading metadata resources as URLs or input
 * streams. Crop identifiers are validated and automatically normalized to a `.json` filename when
 * no extension is provided.
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
     * Ensures the crop identifier is present before metadata path formatting.
     *
     * @param cropId the crop identifier or metadata filename
     * @return the validated crop identifier
     * @throws ResourceLoaderException if the crop identifier is null or blank
     */
    private static String requireCropId(String cropId) throws ResourceLoaderException {
        if (cropId == null || cropId.isBlank()) {
            throw new ResourceLoaderException("Crop identifier cannot be null or blank.");
        }

        return cropId;
    }

    /**
     * Builds a metadata resource path for the given crop identifier.
     *
     * @param cropId the crop identifier or metadata filename
     * @return the formatted metadata path (e.g., "metadata/rice.json" for cropId "rice")
     * @throws ResourceLoaderException if the crop identifier is null or blank
     */
    private static String format(String cropId) {
        String validatedCropId = requireCropId(cropId);
        String filename =
                validatedCropId.endsWith(".json") ? validatedCropId : validatedCropId + ".json";
        return ResourceConstants.METADATA_PATH + "/" + filename;
    }

    /**
     * Resolves the relative metadata resource path for the given crop identifier.
     *
     * @param cropId the crop identifier or metadata filename
     * @return the relative path to the metadata resource
     * @throws ResourceLoaderException if the crop identifier is null or blank
     */
    public static String resolveMetadataPath(String cropId) {
        return format(cropId);
    }

    /**
     * Builds an absolute metadata path for the given crop identifier.
     *
     * @param cropId the crop identifier or metadata filename
     * @return the absolute path to the metadata resource (e.g., "/com/crop/app/metadata/rice.json"
     *         for cropId "rice")
     * @throws ResourceLoaderException if the crop identifier is null or blank
     */
    public static String getMetadataPathAbsolute(String cropId) {
        return ResourceLoader.getAbsolutePath(format(cropId));
    }

    /**
     * Resolves metadata for a crop to a URL.
     *
     * @param cropId the name of the metadata resource (e.g., "rice"). File extension is optional;
     *        if not provided, ".json" will be appended automatically.
     * @return the URL of the metadata resource (e.g., URL of resource "metadata/rice.json" for
     *         cropId "rice")
     * @throws ResourceLoaderException if the crop identifier is null or blank, or if the resource
     *         cannot be found
     */
    public static URL getMetadata(String cropId) {
        return ResourceLoader.getResource(format(cropId));
    }

    /**
     * Opens metadata for a crop as an input stream.
     *
     * @param cropId the name of the metadata resource (e.g., "rice"). File extension is optional;
     *        if not provided, ".json" will be appended automatically.
     * @return an input stream for the specified metadata resource path
     * @throws ResourceLoaderException if the crop identifier is null or blank, or if the resource
     *         stream cannot be opened
     */
    public static InputStream getMetadataStream(String cropId) {
        return ResourceLoader.getResourceStream(format(cropId));
    }
}
