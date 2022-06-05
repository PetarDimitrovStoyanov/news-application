package com.newsAapplicationMicroservice.authmicroservice.util.microserviceRequest;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MicroserviceRequest {
    <T> List<T> getObjects(String url, Class<T> returnTypeClass);

    <T> T getObject(String url, Class<T> returnTypeClass);

    <T> ResponseEntity<?> postObject(String url, T payload);

    <T> ResponseEntity<?> deleteObject(String url, T payload);

    <T> ResponseEntity<?> putObject(String url, T payload);
}
