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

package com.crop.app.infrastructure.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import com.crop.app.common.exception.MetadataReadException;
import com.crop.app.domain.model.Crop;
import com.crop.app.infrastructure.loader.MetadataLoader;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Maps crop metadata JSON into {@link Crop} domain objects.
 *
 * <p>
 * This utility reads crop metadata from classpath resources via {@link MetadataLoader} and
 * deserializes it with Gson.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 14-04-2026
 */
public final class CropMetadataMapper {

    private static final Gson GSON = new Gson();

    /**
     * Prevents instantiation of this utility class.
     */
    private CropMetadataMapper() {
        // Utility class
    }

    /**
     * Reads crop metadata JSON and converts it to a {@link Crop} object.
     *
     * @param cropId the identifier for the crop (e.g., "rice", "wheat")
     * @return a crop object populated from the corresponding JSON metadata
     * @throws MetadataReadException if an error occurs during the mapping process
     */
    public static Crop mapFromJson(String cropId) {
        try (InputStream iStream = MetadataLoader.getMetadataStream(cropId);
                InputStreamReader reader = new InputStreamReader(iStream, StandardCharsets.UTF_8)) {

            return GSON.fromJson(reader, Crop.class);

        } catch (IOException | JsonSyntaxException e) {
            throw new MetadataReadException("Failed to map JSON for crop: " + cropId + " at path: "
                    + MetadataLoader.getMetadata(cropId), e);
        }
    }
}
