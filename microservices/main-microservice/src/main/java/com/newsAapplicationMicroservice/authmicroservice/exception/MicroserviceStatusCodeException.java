package com.newsAapplicationMicroservice.authmicroservice.exception;

public class MicroserviceStatusCodeException extends RuntimeException {
    private final static String MESSAGE = "Microservice: %s respond with status code: %d";

    public MicroserviceStatusCodeException(String microserviceName, Integer statusCode) {
        super(String.format(MESSAGE, microserviceName, statusCode));
    }
}
