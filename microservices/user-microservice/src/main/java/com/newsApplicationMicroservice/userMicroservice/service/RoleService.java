package com.newsApplicationMicroservice.userMicroservice.service;

import com.newsApplicationMicroservice.userMicroservice.dto.RoleDto;

public interface RoleService {
    RoleDto findByName(String name);
}
