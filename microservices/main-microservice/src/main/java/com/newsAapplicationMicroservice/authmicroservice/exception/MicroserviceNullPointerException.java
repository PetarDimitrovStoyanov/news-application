package com.newsAapplicationMicroservice.authmicroservice.exception;

public class MicroserviceNullPointerException extends RuntimeException {
    private static final String MESSAGE = "Microservice: %s has responseBody with null value";

    public MicroserviceNullPointerException(String url) {
        super(String.format(MESSAGE, url));
    }
}
