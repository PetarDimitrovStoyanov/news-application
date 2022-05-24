package com.newsAapplicationMicroservice.authmicroservice.controller;

import com.newsAapplicationMicroservice.authmicroservice.dto.CreateNewDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.NewDTO;
import com.newsAapplicationMicroservice.authmicroservice.util.Api;
import com.newsAapplicationMicroservice.authmicroservice.util.microserviceRequest.MicroserviceRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Api.NEWS)
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class NewController {

    private final MicroserviceRequest microserviceRequest;

    @GetMapping(value = Api.GET_A_NEW_BY_ID)
    public NewDTO getNewById(@PathVariable String newId){
        String url = String.format("http://localhost:8003/news/%s", newId);

        return microserviceRequest.getObject(url, NewDTO.class);
    }

    @GetMapping(value = Api.GET_ALL_NEWS)
    public List<NewDTO> getAllForReaders(){
        String url = "http://localhost:8003/news/get-all";

        return microserviceRequest.getObjects(url, NewDTO.class);
    }

    @GetMapping(value = Api.GET_ALL_NEWS_MANAGEMENT)
    public List<NewDTO> getAllManagement(){
        String url = "http://localhost:8003/news/get-all-management";

        return microserviceRequest.getObjects(url, NewDTO.class);
    }

    @GetMapping(value = Api.GET_ALL_BY_CATEGORY)
    public List<NewDTO> getAllByCategory(@PathVariable String categoryId){
        String url = String.format("http://localhost:8003/news/get-all-by-category/%s", categoryId);

        return microserviceRequest.getObjects(url, NewDTO.class);
    }

    @PostMapping(value = Api.CREATE_A_NEW)
    public void createANew(@RequestBody @Valid CreateNewDTO createNewDTO){
        String url = "http://localhost:8003/news/create-a-new";

        microserviceRequest.postObject(url, createNewDTO);
    }
}
