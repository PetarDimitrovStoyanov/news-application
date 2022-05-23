package com.new_sapplication_microservice.authmicroservice.controller;

import com.new_sapplication_microservice.authmicroservice.dto.UserDTO;
import com.new_sapplication_microservice.authmicroservice.entity.UserEntity;
import com.new_sapplication_microservice.authmicroservice.repository.UserRepository;
import com.new_sapplication_microservice.authmicroservice.util.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class UserController {
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @GetMapping(value = Api.USERS)
    @Transactional
    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        List<UserDTO> userDtos = users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return userDtos;
    }
}
