package com.crop.app.common.exception;

public class UserDatabaseWriteException extends RuntimeException {

    public UserDatabaseWriteException(String message) {
        super(message);
    }

    public UserDatabaseWriteException(String message, Throwable cause) {
        super(message, cause);
    }
}
