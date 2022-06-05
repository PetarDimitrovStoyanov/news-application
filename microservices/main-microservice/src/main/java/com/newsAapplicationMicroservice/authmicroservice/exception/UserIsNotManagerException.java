package com.newsAapplicationMicroservice.authmicroservice.exception;

public class UserIsNotManagerException extends RuntimeException {

    public UserIsNotManagerException(String id) {
        super(String.format("The user with id: %s is not a manager.", id));
    }
}
