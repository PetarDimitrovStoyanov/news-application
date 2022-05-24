package com.newsApplicationMicroservice.userMicroservice.controller;

import com.newsApplicationMicroservice.userMicroservice.dto.ChangeRoleDTO;
import com.newsApplicationMicroservice.userMicroservice.dto.UserDto;
import com.newsApplicationMicroservice.userMicroservice.entity.UserEntity;
import com.newsApplicationMicroservice.userMicroservice.service.UserService;
import com.newsApplicationMicroservice.userMicroservice.util.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/{email}")
    public UserDto getUserByEmail(@PathVariable String email) {
        log.info("Getting a user by email {}", email);

        return userService.getUserByEmail(email);
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        log.info("Getting of all users");

        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserDto userDto) {
        log.info("Creating a user {}", userDto.toString());
        UserEntity user = userService.createUser(userDto);

        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value = Api.CHANGE_USER_ROLE)
    public ResponseEntity<?> changeUserRole(@PathVariable String userId, @RequestBody ChangeRoleDTO changeRoleDTO) {
        log.info("Changing of roles endpoint was reached.");
        userService.changeUserRole(userId, changeRoleDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
