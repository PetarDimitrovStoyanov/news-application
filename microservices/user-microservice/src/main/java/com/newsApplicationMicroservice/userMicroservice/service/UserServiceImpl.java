package com.newsApplicationMicroservice.userMicroservice.service;

import com.newsApplicationMicroservice.userMicroservice.dto.ChangeRoleDTO;
import com.newsApplicationMicroservice.userMicroservice.exceptions.UserNotFoundException;
import com.newsApplicationMicroservice.userMicroservice.repository.UserRepository;
import com.newsApplicationMicroservice.userMicroservice.enums.RoleEnum;
import com.newsApplicationMicroservice.userMicroservice.dto.RoleDto;
import com.newsApplicationMicroservice.userMicroservice.dto.UserDto;
import com.newsApplicationMicroservice.userMicroservice.entity.RoleEntity;
import com.newsApplicationMicroservice.userMicroservice.entity.UserEntity;
import com.newsApplicationMicroservice.userMicroservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));

        return convertUserEntityToDto(userEntity);
    }

    private UserDto convertUserEntityToDto(UserEntity userEntity) {

        return UserDto.builder()
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .id(userEntity.getId().toString())
                .roles(userEntity.getRoles().stream().map(this::mapRoleToDto).collect(Collectors.toList()))
                .picture(userEntity.getPicture())
                .build();
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(this::convertUserEntityToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserEntity createUser(UserDto userDto) {
        RoleEntity roleEntity = roleRepository.findByName(RoleEnum.CREATOR.getName());
        UserEntity userEntity = mapDtoToUserEntity(userDto, roleEntity);
        return userRepository.saveAndFlush(userEntity);
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

    private UserEntity mapDtoToUserEntity(UserDto userDto, RoleEntity roleEntity) {
        return UserEntity.builder()
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .picture(userDto.getPicture())
                .roles(List.of(roleEntity))
                .build();
    }

    private RoleDto mapRoleToDto(RoleEntity role) {

        return RoleDto.builder()
                .id(role.getId().toString())
                .name(role.getName())
                .build();
    }
}
