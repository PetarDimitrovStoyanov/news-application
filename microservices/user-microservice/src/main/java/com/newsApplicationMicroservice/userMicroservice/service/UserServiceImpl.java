package com.newsApplicationMicroservice.userMicroservice.service;

import com.newsApplicationMicroservice.userMicroservice.dto.ChangeRoleDTO;
import com.newsApplicationMicroservice.userMicroservice.dto.UserDTO;
import com.newsApplicationMicroservice.userMicroservice.entity.RoleEntity;
import com.newsApplicationMicroservice.userMicroservice.entity.UserEntity;
import com.newsApplicationMicroservice.userMicroservice.exceptions.UserNotFoundException;
import com.newsApplicationMicroservice.userMicroservice.repository.RoleRepository;
import com.newsApplicationMicroservice.userMicroservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();

        return users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void changeUserRole(String userId, ChangeRoleDTO changeRoleDTO) {
        RoleEntity oldRole = roleRepository.findByName(changeRoleDTO.getOldRole().getName());
        RoleEntity newRole = roleRepository.findByName(changeRoleDTO.getNewRole().getName());

        UserEntity userEntity = userRepository
                .findById(UUID.fromString(userId))
                .orElseThrow(() -> new UserNotFoundException(userId));

        List<RoleEntity> roles = userEntity.getRoles();
        roles.removeIf(role -> role.getName().equalsIgnoreCase(oldRole.getName()));

        if(!roles.contains(newRole)){
            roles.add(newRole);
        }

        userRepository.save(userEntity);
    }

    @Override
    public UserDTO getUserById(String userId) {
        UserEntity userEntity = userRepository
                .findById(UUID.fromString(userId))
                .orElseThrow(() -> new UserNotFoundException(userId));

        return modelMapper.map(userEntity, UserDTO.class);
    }
}
