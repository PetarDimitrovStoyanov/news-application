package com.newsAapplicationMicroservice.authmicroservice.util.microserviceRequest;

import com.newsAapplicationMicroservice.authmicroservice.exception.MicroserviceNullPointerException;
import com.newsAapplicationMicroservice.authmicroservice.exception.MicroserviceStatusCodeException;
import com.newsAapplicationMicroservice.authmicroservice.interceptor.HeaderInterceptor;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MicroserviceRequestImpl implements MicroserviceRequest {
    private final RestTemplate restTemplate;

    private final ModelMapper modelMapper;

    public <T> List<T> getObjects(String url, Class<T> returnTypeClass) {
        addSecretKeyAsHeaderToRequest();
        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(url, Object[].class);

        checkResponseStatusCode(url, responseEntity.getStatusCode());
        checkResponseBody(url, responseEntity.getBody());

        return Arrays.stream(responseEntity.getBody())
                .map(object -> modelMapper.map(object, returnTypeClass))
                .collect(Collectors.toList());
    }

    public <T> T getObject(String url, Class<T> returnTypeClass) {
        addSecretKeyAsHeaderToRequest();
        ResponseEntity<?> responseEntity = restTemplate.getForEntity(url, ResponseEntity.class);

        checkResponseStatusCode(url, responseEntity.getStatusCode());
        checkResponseBody(url, responseEntity.getBody());

        return modelMapper.map(responseEntity.getBody(), returnTypeClass);
    }

    public <T> ResponseEntity<?> postObject(String url, T payload) {
        addSecretKeyAsHeaderToRequest();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", "application/json");
        HttpEntity<T> httpEntity = new HttpEntity<>(payload, headers);

        return restTemplate.postForObject(url, httpEntity, ResponseEntity.class);
    }

    public <T> ResponseEntity<?> deleteObject(String url, T payload) {
        addSecretKeyAsHeaderToRequest();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", "application/json");
        HttpEntity<T> httpEntity = new HttpEntity<>(payload, headers);

        return restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, ResponseEntity.class);
    }

    @Override
    public <T> ResponseEntity<?> putObject(String url, T payload) {
        addSecretKeyAsHeaderToRequest();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", "application/json");
        HttpEntity<T> httpEntity = new HttpEntity<>(payload, headers);

        return restTemplate.exchange(url, HttpMethod.PUT, httpEntity, ResponseEntity.class);
    }

    private void checkResponseBody(String url, Object body) {
        if (body == null) {
            throw new MicroserviceNullPointerException(url);
        }
    }

    private void checkResponseStatusCode(String url, HttpStatus statusCode) {
        List<HttpStatus> successfulStatuses = List.of(HttpStatus.OK, HttpStatus.NO_CONTENT, HttpStatus.CREATED);
        HttpStatus returnStatus = HttpStatus.valueOf(statusCode.value());

        if (!successfulStatuses.contains(returnStatus)) {
            throw new MicroserviceStatusCodeException(url, statusCode.value());
        }
    }

    private void addSecretKeyAsHeaderToRequest() {
        restTemplate.getInterceptors().add(new HeaderInterceptor());
    }
}
