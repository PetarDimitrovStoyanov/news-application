package com.newsAapplicationMicroservice.authmicroservice.util.microserviceRequest;

import com.newsAapplicationMicroservice.authmicroservice.dto.ImageDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.ImageResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MicroserviceRequest {
    <T> List<T> getObjects(String url, Class<T> returnTypeClass);

    <T> T getObject(String url, Class<T> returnTypeClass);

    <T> ResponseEntity<?> postObject(String url, T payload);

    <T> ResponseEntity<?> deleteObject(String url, T payload);

    <T> ResponseEntity<?> putObject(String url, T payload);

    ResponseEntity<ImageDTO> postImage(String url, MultipartFile multipartFile) throws IOException;

    ImageResponseDTO getImage(String url);

    ResponseEntity<?> deleteImage(String url);
}
