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

import java.io.InputStream;
import java.net.URL;
import com.crop.app.common.constants.ResourceConstants;
import com.crop.app.common.exception.ResourceLoaderException;

/**
 * Utility for loading CSS style resources from the classpath.
 *
 * <p>
 * Provides helpers for resolving style paths and reading CSS resources as URLs or input streams.
 * Style identifiers are validated and automatically normalized to a `.css` filename when no
 * extension is provided.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 18-04-2026
 */
public class StyleLoader {
    /**
     * Prevents instantiation of this utility class.
     */
    private StyleLoader() {}

    /**
     * Ensures the style identifier is present before path formatting.
     *
     * @param styleId the style identifier or filename
     * @return the validated style identifier
     * @throws ResourceLoaderException if the style identifier is null or blank
     */
    private static String requireStyleId(String styleId) throws ResourceLoaderException {
        if (styleId == null || styleId.isBlank()) {
            throw new ResourceLoaderException("Style identifier cannot be null or blank.");
        }

        return styleId;
    }

    /**
     * Builds a CSS resource path for the given style identifier.
     *
     * @param styleId the style identifier or filename (e.g., "app.css" for styleId "app")
     * @return the formatted CSS path (e.g., "styles/app.css" for styleId "app")
     * @throws ResourceLoaderException if the style identifier is null or blank
     */
    public static String format(String styleId) {
        String validatedStyleId = requireStyleId(styleId);
        String filename =
                validatedStyleId.endsWith(".css") ? validatedStyleId : validatedStyleId + ".css";
        return ResourceConstants.STYLES_PATH + "/" + filename;
    }

    /**
     * Builds a relative CSS path for the given style identifier.
     *
     * @param style the style identifier or filename (e.g., "app.css" for styleId "app")
     * @return the relative path to the CSS resource (e.g., "styles/app.css" for style "app")
     * @throws ResourceLoaderException if the style identifier is null or blank
     */
    public static String resolveStylePath(String style) {
        return format(style);
    }

    /**
     * Builds an absolute CSS path for the given style identifier.
     *
     * @param style the style identifier or filename (e.g., "app.css" for styleId "app")
     * @return the absolute path to the CSS resource (e.g., "/com/crop/app/styles/app.css" for style
     *         "app")
     * @throws ResourceLoaderException if the style identifier is null or blank
     */
    public static String getStylePathAbsolute(String style) {
        return ResourceLoader.getAbsolutePath(format(style));
    }

    /**
     * Resolves a CSS resource path to a URL.
     *
     * @param style the name of the CSS resource (e.g., "app.css" or "app").
     * @return the URL of the CSS resource
     * @throws ResourceLoaderException if the style identifier is null or blank, or if the resource
     *         cannot be found
     */
    public static URL getStyle(String style) {
        return ResourceLoader.getResource(format(style));
    }

    /**
     * Opens a CSS resource as an input stream.
     *
     * @param style the name of the CSS resource (e.g., "app.css" or "app").
     * @return an input stream for the specified CSS resource path
     * @throws ResourceLoaderException if the style identifier is null or blank, or if the resource
     *         stream cannot be opened
     */
    public static InputStream getStyleStream(String style) {
        return ResourceLoader.getResourceStream(format(style));
    }
}
