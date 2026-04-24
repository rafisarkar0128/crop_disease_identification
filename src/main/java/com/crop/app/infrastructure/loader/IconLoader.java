package com.crop.app.infrastructure.loader;

import java.io.InputStream;
import java.net.URL;
import com.crop.app.common.constants.ResourceConstants;
import com.crop.app.common.exception.ResourceLoaderException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IconLoader {

    private IconLoader() {}

    private static String requireIcon(String icon) throws ResourceLoaderException {
        if (icon == null || icon.isBlank()) {
            throw new ResourceLoaderException("Icon identifier cannot be null or blank.");
        }

        return icon;
    }

    private static String format(String icon) {
        String validatedIcon = requireIcon(icon);
        String lower = validatedIcon.toLowerCase();

        boolean hasExtension =
                lower.endsWith(".png") || lower.endsWith(".jpg") || lower.endsWith(".jpeg");

        String filename = hasExtension ? validatedIcon : validatedIcon + ".png";
        return ResourceConstants.ICONS_PATH + "/" + filename;
    }

    public static String resolveIconPath(String icon) {
        return format(icon);
    }

    public static String getIconPathAbsolute(String icon) {
        return ResourceLoader.getAbsolutePath(format(icon));
    }

    public static URL getIcon(String icon) {
        return ResourceLoader.getResource(format(icon));
    }

    public static InputStream getIconStream(String icon) {
        return ResourceLoader.getResourceStream(format(icon));
    }

    public static Image getIconAsImage(String icon) {
        return new Image(getIcon(icon).toExternalForm());
    }

    public static ImageView getIconAsImageView(String icon) {
        return new ImageView(getIconAsImage(icon));
    }
}
