package com.crop.app.infrastructure.loader;

import java.io.InputStream;
import java.net.URL;
import com.crop.app.common.constants.ResourceConstants;
import com.crop.app.common.exception.ResourceLoaderException;

public class StyleLoader {

    private StyleLoader() {}

    private static String requireStyleId(String styleId) throws ResourceLoaderException {
        if (styleId == null || styleId.isBlank()) {
            throw new ResourceLoaderException("Style identifier cannot be null or blank.");
        }

        return styleId;
    }

    public static String format(String styleId) {
        String validatedStyleId = requireStyleId(styleId);
        String filename =
                validatedStyleId.endsWith(".css") ? validatedStyleId : validatedStyleId + ".css";
        return ResourceConstants.STYLES_PATH + "/" + filename;
    }

    public static String resolveStylePath(String style) {
        return format(style);
    }

    public static String getStylePathAbsolute(String style) {
        return ResourceLoader.getAbsolutePath(format(style));
    }

    public static URL getStyle(String style) {
        return ResourceLoader.getResource(format(style));
    }

    public static InputStream getStyleStream(String style) {
        return ResourceLoader.getResourceStream(format(style));
    }
}
