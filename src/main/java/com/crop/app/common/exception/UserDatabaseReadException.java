package com.crop.app.common.exception;

public class UserDatabaseReadException extends RuntimeException {

    public UserDatabaseReadException(String message) {
        super(message);
    }

    public UserDatabaseReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
