package com.crop.app.infrastructure.loader;

import java.io.InputStream;
import java.net.URL;
import com.crop.app.common.constants.ResourceConstants;
import com.crop.app.common.exception.ResourceLoaderException;

public final class ResourceLoader {

    private ResourceLoader() {}

    public static String getAbsolutePath(String path) throws ResourceLoaderException {
        if (path == null || path.isBlank()) {
            throw new ResourceLoaderException("Resource path cannot be null or blank.");
        }

        if (path.startsWith("/")) {
            return path;
        }

        return "/" + ResourceConstants.BASE_PACKAGE_PATH + "/" + path;
    }

    private static URL resolveResource(String absolutePath) throws ResourceLoaderException {
        var resource = ResourceLoader.class.getResource(absolutePath);

        if (resource == null) {
            throw new ResourceLoaderException("Resource not found at: " + absolutePath
                    + ". Ensure the file exists in src/main/resources" + absolutePath + ".");
        }

        return resource;
    }

    public static URL getResource(String path) throws ResourceLoaderException {
        return resolveResource(getAbsolutePath(path));
    }

    public static String getResourcePath(String path) throws ResourceLoaderException {
        return resolveResource(getAbsolutePath(path)).toExternalForm();
    }

    public static InputStream getResourceStream(String path) throws ResourceLoaderException {
        String absolutePath = getAbsolutePath(path);
        InputStream inputStream = ResourceLoader.class.getResourceAsStream(absolutePath);

        if (inputStream == null) {
            throw new ResourceLoaderException("Stream could not be opened for: " + absolutePath
                    + ". Ensure the file exists in src/main/resources" + absolutePath + ".");
        }

        return inputStream;
    }
}
