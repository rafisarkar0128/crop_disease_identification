package com.crop.app.infrastructure.loader;

import java.io.InputStream;
import java.net.URL;
import com.crop.app.common.constants.ResourceConstants;
import com.crop.app.common.exception.FxmlLoaderException;

public class FxmlLoader {

    private FxmlLoader() {}

    private static String requireFxmlId(String fxmlId) throws FxmlLoaderException {
        if (fxmlId == null || fxmlId.isBlank()) {
            throw new FxmlLoaderException("FXML identifier cannot be null or blank.");
        }

        return fxmlId;
    }

    public static String format(String fxmlId) {
        String validatedFxmlId = requireFxmlId(fxmlId);
        String filename =
                validatedFxmlId.endsWith(".fxml") ? validatedFxmlId : validatedFxmlId + ".fxml";
        return ResourceConstants.FXML_PATH + "/" + filename;
    }

    public static String resolveFxmlPath(String fxml) {
        return format(fxml);
    }

    public static String getFxmlPathAbsolute(String fxml) {
        return ResourceLoader.getAbsolutePath(format(fxml));
    }

    public static URL getFxml(String fxml) {
        return ResourceLoader.getResource(format(fxml));
    }

    public static InputStream getFxmlStream(String fxml) {
        return ResourceLoader.getResourceStream(format(fxml));
    }
}
