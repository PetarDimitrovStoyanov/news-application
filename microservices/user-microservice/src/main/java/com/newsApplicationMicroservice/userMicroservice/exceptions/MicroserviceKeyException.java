package com.newsApplicationMicroservice.userMicroservice.exceptions;

public class MicroserviceKeyException extends RuntimeException {
    private static final String MESSAGE = "The microservice access keys do not match.";

    public MicroserviceKeyException() {
        super(MESSAGE);
    }
}
