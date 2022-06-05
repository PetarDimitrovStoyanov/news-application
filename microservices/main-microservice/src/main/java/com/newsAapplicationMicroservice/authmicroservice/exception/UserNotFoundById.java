package com.newsAapplicationMicroservice.authmicroservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundById extends RuntimeException {

    public UserNotFoundById(String id) {
        super(String.format("User with id: %s was not found.", id));
    }
}
