package com.new_sapplication_microservice.new_microservice.controller;

import com.new_sapplication_microservice.new_microservice.dto.CreateANewDTO;
import com.new_sapplication_microservice.new_microservice.dto.NewDTO;
import com.new_sapplication_microservice.new_microservice.entity.NewEntity;
import com.new_sapplication_microservice.new_microservice.service.NewService;
import com.new_sapplication_microservice.new_microservice.util.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Api.NEWS)
@RequiredArgsConstructor
@Slf4j
public class NewController {
    private final NewService newService;

    @GetMapping(value = Api.GET_ALL)
    public ResponseEntity<List<NewDTO>> getAllNews() {
        log.info("Getting all news");
        List<NewDTO> news = newService.findAllNews();

        return ResponseEntity.ok().body(news);
    }

    @GetMapping(Api.GET_BY_ID)
    public ResponseEntity<NewDTO> getANewById(@PathVariable String newId) {
        log.info("Getting a new by newId: {}", newId);
        NewDTO dto = newService.findById(newId);

        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<NewEntity> createANew(@RequestBody @Valid CreateANewDTO newDto) {
        log.info("Creating a new {}", newDto);

        NewEntity aNew = newService.createANew(newDto);
        return ResponseEntity.ok().body(aNew);
    }
}
