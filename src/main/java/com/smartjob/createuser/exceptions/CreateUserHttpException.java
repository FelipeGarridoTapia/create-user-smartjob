package com.smartjob.createuser.exceptions;

public class CreateUserHttpException extends Exception {
    private final String error;
    private final String message;

    public CreateUserHttpException(String error, String message) {
        super(message);
        this.error = error;
        this.message = message;
    }
}
