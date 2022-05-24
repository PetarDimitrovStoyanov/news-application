package com.newsAapplicationMicroservice.authmicroservice.util.microserviceRequest;

import java.util.List;

public interface MicroserviceRequest {
    <T> List<T> getObjects(String url, Class<T> returnTypeClass);

    <T> T getObject(String url, Class<T> returnTypeClass);

    <T> void postObject(String url, T payload);
}
