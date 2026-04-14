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
import com.crop.app.common.exception.MetadataReadException;
import com.crop.app.domain.model.Crop;
import com.crop.app.infrastructure.loader.ResourceLoader;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * CropMetadataMapper is responsible for converting JSON metadata files into Crop domain objects. It
 * uses the Gson library to parse JSON data and populate the fields of the Crop class. This
 * separation of concerns allows the repositories to focus on data access while the mapper handles
 * the transformation logic. The mapFromJson method takes a crop identifier (e.g., "rice") and
 * attempts to read the corresponding JSON file from the resources, returning a fully populated Crop
 * object or null if an error occurs during the mapping process.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 14-04-2026
 */
public class CropMetadataMapper {
    /**
     * Maps JSON metadata for a crop to a Crop domain object.
     *
     * @param cropId The identifier for the crop (e.g., "rice", "wheat")
     * @return A Crop object populated with data from the corresponding JSON file, or null if an
     *         error occurs during mapping.
     */
    public static Crop mapFromJson(String cropId) {
        Gson gson = new Gson();

        try (InputStream iStream = ResourceLoader.getMetadataStream(cropId);
                InputStreamReader reader = new InputStreamReader(iStream)) {

            return gson.fromJson(reader, Crop.class);

        } catch (IOException | JsonSyntaxException e) {
            throw new MetadataReadException("Failed to map JSON for crop: " + cropId + " at path: "
                    + ResourceLoader.getMetadata(cropId), e);
        }

    }
}
