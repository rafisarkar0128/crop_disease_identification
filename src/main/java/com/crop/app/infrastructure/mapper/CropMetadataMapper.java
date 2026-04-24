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

public final class CropMetadataMapper {

    private static final Gson GSON = new Gson();

    private CropMetadataMapper() {}

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
