package com.crop.app.common.exception;

public class MetadataReadException extends RuntimeException {

    public MetadataReadException(String message) {
        super(message);
    }

    public MetadataReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
