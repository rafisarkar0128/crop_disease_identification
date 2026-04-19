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

package com.crop.app.common.exception;

/**
 * Exception thrown when an error occurs during FXML loading, such as when the FXML file cannot be
 * found, is malformed, or when the controller cannot be instantiated.
 *
 * <p>
 * Used by the FXML resource loader when a required resource path cannot be resolved to a URL or
 * input stream.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 19-04-2026
 */
public class FxmlLoaderException extends RuntimeException {

    /**
     * Creates an exception with a detail message.
     *
     * @param message the detail message
     */
    public FxmlLoaderException(String message) {
        super(message);
    }

    /**
     * Creates an exception with a detail message and root cause.
     *
     * @param message the detail message
     * @param cause the underlying cause
     */
    public FxmlLoaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
