package com.new_sapplication_microservice.new_microservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundCategoryException extends RuntimeException {

    public NotFoundCategoryException(String id) {
        super(String.format("The category with name: %s was not found!", id));
    }
}
