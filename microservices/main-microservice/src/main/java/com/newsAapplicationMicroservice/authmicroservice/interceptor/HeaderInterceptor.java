package com.newsAapplicationMicroservice.authmicroservice.interceptor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class HeaderInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        HttpHeaders headers = request.getHeaders();
        headers.add("key", System.getenv("microservice.key"));

        return execution.execute(request, body);
    }
}
