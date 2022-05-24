package com.newsApplicationMicroservice.userMicroservice.service;

import com.newsApplicationMicroservice.userMicroservice.enums.RoleEnum;
import com.newsApplicationMicroservice.userMicroservice.dto.RoleDTO;
import com.newsApplicationMicroservice.userMicroservice.entity.RoleEntity;
import com.newsApplicationMicroservice.userMicroservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    public RoleDTO findByName(String name) {
        RoleEntity roleEntity = roleRepository.findByName(RoleEnum.valueOf(name).getName());

        return modelMapper.map(roleEntity, RoleDTO.class);
    }
}
