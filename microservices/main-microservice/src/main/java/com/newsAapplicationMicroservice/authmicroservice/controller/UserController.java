package com.newsAapplicationMicroservice.authmicroservice.controller;

import com.newsAapplicationMicroservice.authmicroservice.dto.UserDTO;
import com.newsAapplicationMicroservice.authmicroservice.entity.UserEntity;
import com.newsAapplicationMicroservice.authmicroservice.repository.UserRepository;
import com.newsAapplicationMicroservice.authmicroservice.util.Api;
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
@RequestMapping(Api.USERS)
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class UserController {
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @GetMapping(value = Api.ALL)
    @Transactional
    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();

        return users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }
}
