package com.newsAapplicationMicroservice.authmicroservice.controller;

import com.newsAapplicationMicroservice.authmicroservice.dto.ChangeRoleDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.UserDTO;
import com.newsAapplicationMicroservice.authmicroservice.util.Api;
import com.newsAapplicationMicroservice.authmicroservice.util.microserviceRequest.MicroserviceRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(Api.USERS)
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class UserController {

    private final MicroserviceRequest microserviceRequest;

    @GetMapping(value = Api.ALL)
    public List<UserDTO> getAllUsers() {
        String url = "http://localhost:8001/users/all";

        return microserviceRequest.getObjects(url, UserDTO.class);
    }

    @PostMapping(value = Api.CHANGE_USER_ROLE)
    public ResponseEntity<?> changeUserRole(@PathVariable String userId, @RequestBody @Valid ChangeRoleDTO changeRoleDTO) {
        String url = String.format("http://localhost:8001/users/%s/change-role", userId);
        microserviceRequest.postObject(url, changeRoleDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}