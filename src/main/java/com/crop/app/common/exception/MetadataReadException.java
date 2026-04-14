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
 * Unchecked exception for metadata parsing and metadata read failures.
 *
 * <p>
 * Used by the metadata mapping flow to signal that crop metadata could not be converted into domain
 * objects.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 14-04-2026
 */
public class MetadataReadException extends RuntimeException {
    /**
     * Creates an exception with a detail message.
     *
     * @param message the detail message
     */
    public MetadataReadException(String message) {
        super(message);
    }

    /**
     * Creates an exception with a detail message and root cause.
     *
     * @param message the detail message
     * @param cause the underlying cause
     */
    public MetadataReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
