package com.newsAapplicationMicroservice.authmicroservice.controller;

import com.newsAapplicationMicroservice.authmicroservice.dto.CreateNewDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.DeleteRequestMainMicroserviceDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.DeleteRequestNewsMicroserviceDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.EditRequestMainMicroserviceDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.EditRequestNewsMicroserviceDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.NewDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.StatusRequestMainDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.StatusRequestNewsDTO;
import com.newsAapplicationMicroservice.authmicroservice.service.UserEntityService;
import com.newsAapplicationMicroservice.authmicroservice.util.Api;
import com.newsAapplicationMicroservice.authmicroservice.util.microserviceRequest.MicroserviceRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    private final UserEntityService userEntityService;

    @GetMapping(value = Api.GET_A_NEW_BY_ID)
    public NewDTO getNewById(@PathVariable String newId) {
        log.info("The endpoint for getting a new with id {} was reached.", newId);
        final String url = String.format("http://localhost:8003/news/%s", newId);

        return microserviceRequest.getObject(url, NewDTO.class);
    }

    @GetMapping(value = Api.GET_ALL_NEWS)
    public List<NewDTO> getAllForReaders() {
        log.info("The endpoint for getting of all news was reached.");
        final String url = "http://localhost:8003/news/get-all";

        return microserviceRequest.getObjects(url, NewDTO.class);
    }

    @GetMapping(value = Api.GET_ALL_NEWS_MANAGEMENT)
    public List<NewDTO> getAllManagement() {
        log.info("The endpoint for getting of all news with management purposes was reached.");
        final String url = "http://localhost:8003/news/get-all-management";

        return microserviceRequest.getObjects(url, NewDTO.class);
    }

    @GetMapping(value = Api.GET_ALL_BY_CATEGORY)
    public List<NewDTO> getAllByCategory(@PathVariable String categoryId) {
        log.info("The endpoint for getting of all news by category by id: {} was reached.", categoryId);
        final String url = String.format("http://localhost:8003/news/get-all-by-category/%s", categoryId);

        return microserviceRequest.getObjects(url, NewDTO.class);
    }

    @PostMapping(value = Api.CREATE_A_NEW)
    public ResponseEntity<?> createANew(@RequestBody @Valid CreateNewDTO createNewDTO) {
        log.info("The endpoint for creating a new was reached");
        final String url = "http://localhost:8003/news/create-a-new";

        return microserviceRequest.postObject(url, createNewDTO);
    }

    @PostMapping(value = Api.ADD_VIEW)
    public ResponseEntity<?> addViewToProject(@PathVariable String projectId) {
        log.info("The endpoint for adding a view to project with id: {}. was reached", projectId);
        final String url = String.format("http://localhost:8003/news/add-view/%s", projectId);

        return microserviceRequest.postObject(url, projectId);
    }

    @DeleteMapping(value = Api.DELETE_A_NEW)
    public ResponseEntity<?> deleteById(@RequestBody @Valid DeleteRequestMainMicroserviceDTO mainDTO) {
        log.info("The endpoint for deleting a project with id: {} was reached.", mainDTO.getId());
        final String url = "http://localhost:8003/news/delete-a-new";
        DeleteRequestNewsMicroserviceDTO deleteDTO = userEntityService.convertDeleteDTO(mainDTO);

        return microserviceRequest.deleteObject(url, deleteDTO);
    }

    @PutMapping(value = Api.EDIT_A_NEW)
    public ResponseEntity<?> editANew(@RequestBody @Valid EditRequestMainMicroserviceDTO mainDTO) {
        log.info("The endpoint for editing of a project with id: {} was reached.", mainDTO.getId());
        final String url = "http://localhost:8003/news/edit-a-new";
        EditRequestNewsMicroserviceDTO editDTO = userEntityService.convertEditDTO(mainDTO);

        return microserviceRequest.putObject(url, editDTO);
    }

    @PostMapping(value = Api.CHANGE_NEW_STATUS)
    public ResponseEntity<?> changeStatus(@RequestBody @Valid StatusRequestMainDTO mainDTO) {
        log.info("The endpoint for changing of project's status was reached - project id: {}", mainDTO.getId());
        final String url = "http://localhost:8003/news/status-change";
        StatusRequestNewsDTO newsDTO = userEntityService.convertStatusDTO(mainDTO);

        return microserviceRequest.postObject(url, newsDTO);
    }
}
