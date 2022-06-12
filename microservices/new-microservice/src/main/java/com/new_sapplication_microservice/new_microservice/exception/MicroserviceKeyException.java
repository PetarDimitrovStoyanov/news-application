package com.new_sapplication_microservice.new_microservice.exception;

public class MicroserviceKeyException extends RuntimeException {
    private static final String MESSAGE = "The microservice access keys do not match.";

    public MicroserviceKeyException() {
        super(MESSAGE);
    }
}
