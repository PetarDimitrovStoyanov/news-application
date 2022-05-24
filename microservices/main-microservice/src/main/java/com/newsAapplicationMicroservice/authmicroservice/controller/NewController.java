package com.newsAapplicationMicroservice.authmicroservice.controller;

import com.newsAapplicationMicroservice.authmicroservice.dto.NewDTO;
import com.newsAapplicationMicroservice.authmicroservice.util.Api;
import com.newsAapplicationMicroservice.authmicroservice.util.microserviceRequest.MicroserviceRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<NewDTO> getAll(){
        String url = "http://localhost:8003/news/get-all";

        return microserviceRequest.getObjects(url, NewDTO.class);
    }
}
