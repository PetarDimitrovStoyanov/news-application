package com.new_sapplication_microservice.new_microservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserDoesNotHavePermissionException extends RuntimeException {
    private final static String MESSAGE = "The user does not have permissions.";

    public UserDoesNotHavePermissionException() {
        super(MESSAGE);
    }
}
