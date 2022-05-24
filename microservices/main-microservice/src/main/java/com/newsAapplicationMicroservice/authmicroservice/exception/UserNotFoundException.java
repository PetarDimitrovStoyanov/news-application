package com.newsAapplicationMicroservice.authmicroservice.exception;

public class UserNotFoundException extends RuntimeException {

    private final static String MESSAGE = "Wrong email or password.";
    public UserNotFoundException() {
        super(MESSAGE);
    }
}
