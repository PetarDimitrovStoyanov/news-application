package com.newsApplicationMicroservice.userMicroservice.service;

import com.newsApplicationMicroservice.userMicroservice.dto.ChangeRoleDTO;
import com.newsApplicationMicroservice.userMicroservice.dto.UserDto;
import com.newsApplicationMicroservice.userMicroservice.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserDto getUserByEmail(String email);

    List<UserDto> getAllUsers();

    UserEntity createUser(UserDto userDto);

    void changeUserRole(String userId, ChangeRoleDTO changeRoleDTO);
}
