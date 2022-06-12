package com.newsAapplicationMicroservice.authmicroservice.controller;

import com.newsAapplicationMicroservice.authmicroservice.dto.ImageDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.ImageResponseDTO;
import com.newsAapplicationMicroservice.authmicroservice.util.Api;
import com.newsAapplicationMicroservice.authmicroservice.util.microserviceRequest.MicroserviceRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(Api.IMAGES)
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class ImageController {

    private final MicroserviceRequest microserviceRequest;

    @GetMapping(value = Api.GET_IMAGE_BY_ID)
    public ResponseEntity<byte[]> getImageById(@PathVariable String imageId){
        log.info("The endpoint for saving an image by was reached successfully.");
        final String url = String.format("http://localhost:8005/pictures/get-a-picture/%s", imageId);
        ImageResponseDTO image = microserviceRequest.getImage(url);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image.getData());
    }

    @PostMapping(value = Api.SAVE_IMAGE)
    public ResponseEntity<ImageDTO> saveImage(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        log.info("The endpoint for saving an image by was reached successfully.");
        final String url = "http://localhost:8005/pictures/save-image";

        return microserviceRequest.postImage(url, multipartFile);
    }

    @DeleteMapping(value = Api.DELETE_IMAGE_BY_ID)
    public ResponseEntity<?> deleteImageById(@PathVariable String imageId){
        log.info("The endpoint for deleting an image by id: {} was reached successfully.", imageId);
        final String url = String.format("http://localhost:8005/pictures/delete/%s", imageId);

        return microserviceRequest.deleteImage(url);
    }
}
