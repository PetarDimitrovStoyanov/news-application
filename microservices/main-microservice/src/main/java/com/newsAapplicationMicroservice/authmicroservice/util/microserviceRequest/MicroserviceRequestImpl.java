package com.newsAapplicationMicroservice.authmicroservice.util.microserviceRequest;

import com.newsAapplicationMicroservice.authmicroservice.dto.ImageDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.ImageResponseDTO;
import com.newsAapplicationMicroservice.authmicroservice.exception.MicroserviceNullPointerException;
import com.newsAapplicationMicroservice.authmicroservice.exception.MicroserviceStatusCodeException;
import com.newsAapplicationMicroservice.authmicroservice.interceptor.HeaderInterceptor;
import com.newsAapplicationMicroservice.authmicroservice.util.Api;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class MicroserviceRequestImpl implements MicroserviceRequest {

    private RestTemplate restTemplate;

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
        ResponseEntity<?> responseEntity = restTemplate.getForEntity(url, Object.class);

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

    public ImageResponseDTO getImage(String url) {
        addSecretKeyAsHeaderToRequest();

        byte[] image = restTemplate.getForObject(url, byte[].class);
        ImageResponseDTO imageResponseDTO = new ImageResponseDTO();
        imageResponseDTO.setData(image);

        return imageResponseDTO;
    }

    public ResponseEntity<ImageDTO> postImage(String url, MultipartFile multipartFile) throws IOException {
        addSecretKeyAsHeaderToRequest();
        ResponseEntity<ImageDTO> exchange = null;

        boolean isFileCreated = createFileFromMultipartFile(multipartFile);
        Path imgPath = getImagePathFromFilesLocation(Api.IMAGE_FILE_PATH);

        if (isFileCreated) {
            MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
            parts.add("image", new UrlResource(imgPath.toAbsolutePath().toUri()));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(parts, headers);
            exchange = restTemplate.exchange(url, HttpMethod.POST, request, ImageDTO.class);
        }
        Files.delete(imgPath);

        return ResponseEntity.ok().body(exchange.getBody());
    }

    private Path getImagePathFromFilesLocation(String path) throws IOException {
        Stream<Path> fileToUpload = Files.list(Paths.get(path));

        return fileToUpload.collect(Collectors.toList()).get(0);
    }

    private boolean createFileFromMultipartFile(MultipartFile multipartFile) throws IOException {
        File file = new File(Api.IMAGE_FILE_PATH + multipartFile.getOriginalFilename());
        boolean isFileCreated = file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();

        return isFileCreated;
    }

    public ResponseEntity<?> deleteImage(String url) {
        addSecretKeyAsHeaderToRequest();

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> httpEntity = new HttpEntity<>(null, headers);
        restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, Void.class);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
