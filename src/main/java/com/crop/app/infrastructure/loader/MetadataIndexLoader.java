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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import com.crop.app.common.constants.ResourceConstants;
import com.crop.app.common.exception.MetadataReadException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Loads and parses metadata index resources.
 *
 * <p>
 * Provides helper methods for resolving active crop metadata files from
 * {@code metadata-index.json}.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 23-04-2026
 */
public final class MetadataIndexLoader {
    /**
     * Private constructor to prevent instantiation.
     */
    private MetadataIndexLoader() {
        // Utility class
    }

    /**
     * Loads file names for active crop metadata entries from metadata index.
     *
     * @return list of metadata file names, such as {@code rice.json}
     * @throws MetadataReadException if index file cannot be read or parsed
     */
    public static List<String> loadActiveMetadataFiles() {
        String indexPath = ResourceConstants.METADATA_PATH + "/" + ResourceConstants.METADATA_INDEX;

        try (InputStream inputStream = ResourceLoader.getResourceStream(indexPath);
                InputStreamReader reader =
                        new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {

            JsonObject indexRoot = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray items = indexRoot.getAsJsonArray("items");
            List<String> metadataFiles = new ArrayList<>();

            if (items != null) {
                for (JsonElement itemElement : items) {
                    JsonObject item = itemElement.getAsJsonObject();

                    String status = getJsonString(item, "status");
                    if (status != null && !status.equalsIgnoreCase("active")) {
                        continue;
                    }

                    String file = getJsonString(item, "file");
                    if (file != null && !file.isBlank()) {
                        metadataFiles.add(file.trim());
                    }
                }
            }

            return List.copyOf(metadataFiles);
        } catch (IOException exception) {
            throw new MetadataReadException("Failed to read metadata index at path: " + indexPath,
                    exception);
        }
    }

    /**
     * Helper to safely extract string properties from JSON objects.
     *
     * @param item JSON object to extract from
     * @param property property name to extract
     * @return string value if present and non-null, otherwise null
     */
    private static String getJsonString(JsonObject item, String property) {
        return item != null && item.has(property) && !item.get(property).isJsonNull()
                ? item.get(property).getAsString()
                : null;
    }
}
