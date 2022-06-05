package com.new_sapplication_microservice.new_microservice.controller;

import com.new_sapplication_microservice.new_microservice.dto.CreateANewDTO;
import com.new_sapplication_microservice.new_microservice.dto.EditDTO;
import com.new_sapplication_microservice.new_microservice.dto.NewDTO;
import com.new_sapplication_microservice.new_microservice.dto.DeleteDTO;
import com.new_sapplication_microservice.new_microservice.dto.StatusDTO;
import com.new_sapplication_microservice.new_microservice.entity.NewEntity;
import com.new_sapplication_microservice.new_microservice.service.NewService;
import com.new_sapplication_microservice.new_microservice.util.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<NewDTO>> getAllByIsActiveTrueOrderByCreatedDateDesc() {
        log.info("Getting all news");
        List<NewDTO> news = newService.findAllByIsActiveTrueOrderByCreatedDateDesc();

        return ResponseEntity.ok().body(news);
    }

    @GetMapping(value = Api.GET_ALL_MANAGEMENT)
    public ResponseEntity<List<NewDTO>> getAllManagement() {
        log.info("Getting all news for management purposes");
        List<NewDTO> news = newService.findAllManagement();

        return ResponseEntity.ok().body(news);
    }

    @GetMapping(Api.GET_BY_ID)
    public ResponseEntity<NewDTO> getANewById(@PathVariable String newId) {
        log.info("Getting a new by newId: {}", newId);
        NewDTO dto = newService.findById(newId);

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = Api.GET_ALL_BY_CATEGORY)
    public ResponseEntity<List<NewDTO>> getAllByCategory(@PathVariable String categoryId) {
        log.info("Getting all news by categoryId: {}", categoryId);
        List<NewDTO> news = newService.findAllByCategory(categoryId);

        return ResponseEntity.ok().body(news);
    }

    @PostMapping(value = Api.CREATE_A_NEW)
    public ResponseEntity<NewEntity> createANew(@RequestBody @Valid CreateANewDTO newDto) {
        log.info("Creating a new {}", newDto);
        NewEntity aNew = newService.createANew(newDto);

        return ResponseEntity.ok().body(aNew);
    }

    @PostMapping(value = Api.ADD_VIEW)
    public ResponseEntity<?> addViewToProject(@PathVariable String projectId) {
        log.info("Add view to project with id: {}", projectId);
        newService.addViewToProject(projectId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = Api.DELETE_A_NEW)
    public ResponseEntity<?> deleteById(@RequestBody @Valid DeleteDTO deleteDTO) {
        log.info("Delete project with id: {}", deleteDTO.getId());
        newService.deleteById(deleteDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = Api.EDIT_BY_ID)
    public ResponseEntity<?> editById(@RequestBody @Valid EditDTO editDTO) {
        log.info("Edit project with id: {}", editDTO.getId());
        newService.editProject(editDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = Api.CHANGE_STATUS)
    public ResponseEntity<?> changeStatus(@RequestBody @Valid StatusDTO statusDTO) {
        log.info("Change status of project with id: {}", statusDTO.getId());
        newService.changeStatus(statusDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
