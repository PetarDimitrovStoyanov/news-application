package com.newsApplicationMicroservice.userMicroservice.service;

import com.newsApplicationMicroservice.userMicroservice.dto.RoleDTO;

public interface RoleService {
    RoleDTO findByName(String name);
}
