package com.crop.app.infrastructure.loader;

import java.io.InputStream;
import java.net.URL;
import com.crop.app.common.constants.ResourceConstants;
import com.crop.app.common.exception.ResourceLoaderException;

public class MetadataLoader {

    private MetadataLoader() {}

    private static String requireCropId(String cropId) throws ResourceLoaderException {
        if (cropId == null || cropId.isBlank()) {
            throw new ResourceLoaderException("Crop identifier cannot be null or blank.");
        }

        return cropId;
    }

    private static String format(String cropId) {
        String validatedCropId = requireCropId(cropId);
        String filename =
                validatedCropId.endsWith(".json") ? validatedCropId : validatedCropId + ".json";
        return ResourceConstants.METADATA_PATH + "/" + filename;
    }

    public static String resolveMetadataPath(String cropId) {
        return format(cropId);
    }

    public static String getMetadataPathAbsolute(String cropId) {
        return ResourceLoader.getAbsolutePath(format(cropId));
    }

    public static URL getMetadata(String cropId) {
        return ResourceLoader.getResource(format(cropId));
    }

    public static InputStream getMetadataStream(String cropId) {
        return ResourceLoader.getResourceStream(format(cropId));
    }
}
