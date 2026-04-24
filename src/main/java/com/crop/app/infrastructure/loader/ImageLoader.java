package com.crop.app.infrastructure.loader;

import java.io.InputStream;
import java.net.URL;
import com.crop.app.common.constants.ResourceConstants;
import com.crop.app.common.exception.ResourceLoaderException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageLoader {

    private ImageLoader() {}

    private static String requireImage(String image) throws ResourceLoaderException {
        if (image == null || image.isBlank()) {
            throw new ResourceLoaderException("Image identifier cannot be null or blank.");
        }

        return image;
    }

    private static String format(String image) {
        String validatedImage = requireImage(image);
        String lower = validatedImage.toLowerCase();

        boolean hasExtension =
                lower.endsWith(".png") || lower.endsWith(".jpg") || lower.endsWith(".jpeg");

        String filename = hasExtension ? validatedImage : validatedImage + ".png";
        return ResourceConstants.IMAGES_PATH + "/" + filename;
    }

    public static String resolveImagePath(String image) {
        return format(image);
    }

    public static String getImagePathAbsolute(String image) {
        return ResourceLoader.getAbsolutePath(format(image));
    }

    public static URL getImage(String image) {
        return ResourceLoader.getResource(format(image));
    }

    public static InputStream getImageStream(String image) {
        return ResourceLoader.getResourceStream(format(image));
    }

    public static Image getImageAsImage(String image) {
        return new Image(getImage(image).toExternalForm());
    }

    public static ImageView getImageAsImageView(String image) {
        return new ImageView(getImageAsImage(image));
    }
}
