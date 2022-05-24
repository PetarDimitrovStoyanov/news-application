package com.newsApplicationMicroservice.userMicroservice.service;

import com.newsApplicationMicroservice.userMicroservice.dto.ChangeRoleDTO;
import com.newsApplicationMicroservice.userMicroservice.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    void changeUserRole(String userId, ChangeRoleDTO changeRoleDTO);

    UserDTO getUserById(String userId);
}
