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
 * ResourceLoaderException is a custom exception class that extends RuntimeException. It is used to
 * indicate errors that occur during the resource loading process in the application. This exception
 * can be thrown when a resource (such as an image, model, or metadata file) cannot be found,
 * accessed, or read properly. By creating a specific exception for resource loading issues, it
 * allows for more precise error handling and better debugging when problems arise in the
 * ResourceLoader class or related components.
 *
 * @author Md. Rafi Sarkar (rafisarkar0128)
 * @version 1.0
 * @since 14-04-2026
 */
public class ResourceLoaderException extends RuntimeException {

    /**
     * Constructor that accepts a message describing the exception.
     *
     * @param message The error message describing the exception.
     */
    public ResourceLoaderException(String message) {
        super(message);
    }

    /**
     * Constructor that accepts a message and a "cause" (another exception). This is useful if an
     * IOException caused your resource failure.
     *
     * @param message The error message describing the exception.
     * @param cause The underlying cause of the exception.
     */
    public ResourceLoaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
