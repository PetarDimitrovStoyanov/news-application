package com.newsApplicationMicroservice.userMicroservice.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
        super(String.format("The user with email: %s was not found!", email));
    }
}
