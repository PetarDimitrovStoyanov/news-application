package com.new_sapplication_microservice.new_microservice.exception;

public class UserIsNotAuthorException extends RuntimeException {

    public UserIsNotAuthorException(String userId, String projectId) {
        super(String.format("The user with id: %s is not the author of project id: %s", userId, projectId));
    }
}
