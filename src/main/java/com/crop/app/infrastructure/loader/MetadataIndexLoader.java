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

public final class MetadataIndexLoader {

    private MetadataIndexLoader() {}

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

    private static String getJsonString(JsonObject item, String property) {
        return item != null && item.has(property) && !item.get(property).isJsonNull()
                ? item.get(property).getAsString()
                : null;
    }
}
