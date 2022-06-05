package com.newsAapplicationMicroservice.authmicroservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    private final static String MESSAGE = "Wrong email or password.";

    public UserNotFoundException() {
        super(MESSAGE);
    }
}
