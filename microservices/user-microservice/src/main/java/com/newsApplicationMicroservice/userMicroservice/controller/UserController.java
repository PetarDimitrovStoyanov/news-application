package com.newsApplicationMicroservice.userMicroservice.controller;

import com.newsApplicationMicroservice.userMicroservice.dto.ChangeRoleDTO;
import com.newsApplicationMicroservice.userMicroservice.dto.UserDTO;
import com.newsApplicationMicroservice.userMicroservice.service.UserService;
import com.newsApplicationMicroservice.userMicroservice.util.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Api.USERS)
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping(value = Api.USER_BY_ID)
    public UserDTO getUserById(@PathVariable String userId){
        log.info("Getting a user by id {}", userId);

        return userService.getUserById(userId);
    }

    @GetMapping(Api.ALL)
    public List<UserDTO> getAllUsers() {
        log.info("Getting of all users");

        return userService.getAllUsers();
    }

    @PostMapping(value = Api.CHANGE_USER_ROLE)
    public ResponseEntity<?> changeUserRole(@PathVariable String userId, @RequestBody ChangeRoleDTO changeRoleDTO) {
        log.info("Changing of roles endpoint was reached.");
        userService.changeUserRole(userId, changeRoleDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
