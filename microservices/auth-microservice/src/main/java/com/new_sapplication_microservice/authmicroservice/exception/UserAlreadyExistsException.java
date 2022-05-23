package com.new_sapplication_microservice.authmicroservice.exception;

public class UserAlreadyExistsException extends RuntimeException {
    private static final String MESSAGE = "THe user with email %s already exists.";

    public UserAlreadyExistsException(String email) {
        super(String.format(MESSAGE, email));
    }
}
