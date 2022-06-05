package com.newsAapplicationMicroservice.authmicroservice.service;

import com.newsAapplicationMicroservice.authmicroservice.dto.DeleteRequestMainMicroserviceDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.DeleteRequestNewsMicroserviceDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.EditRequestMainMicroserviceDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.EditRequestNewsMicroserviceDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.StatusRequestMainDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.StatusRequestNewsDTO;
import com.newsAapplicationMicroservice.authmicroservice.entity.RoleEntity;
import com.newsAapplicationMicroservice.authmicroservice.entity.UserEntity;
import com.newsAapplicationMicroservice.authmicroservice.enums.RoleEnum;
import com.newsAapplicationMicroservice.authmicroservice.exception.UserIsNotManagerException;
import com.newsAapplicationMicroservice.authmicroservice.exception.UserNotFoundById;
import com.newsAapplicationMicroservice.authmicroservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserEntityServiceImpl implements UserEntityService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Override
    public EditRequestNewsMicroserviceDTO convertEditDTO(EditRequestMainMicroserviceDTO mainEditDTO) {
        checkIsUserManager(mainEditDTO.getCurrentUserId());

        EditRequestNewsMicroserviceDTO editDTO = modelMapper.map(mainEditDTO, EditRequestNewsMicroserviceDTO.class);
        editDTO.setIsManager(true);

        return editDTO;
    }

    @Override
    public DeleteRequestNewsMicroserviceDTO convertDeleteDTO(DeleteRequestMainMicroserviceDTO mainDTO) {
        checkIsUserManager(mainDTO.getCurrentUserId());

        DeleteRequestNewsMicroserviceDTO deleteDTO = modelMapper.map(mainDTO, DeleteRequestNewsMicroserviceDTO.class);
        deleteDTO.setIsManager(true);

        return deleteDTO;
    }

    @Override
    public StatusRequestNewsDTO convertStatusDTO(StatusRequestMainDTO mainDTO) {
        checkIsUserManager(mainDTO.getCurrentUserId());
        StatusRequestNewsDTO statusDTO = modelMapper.map(mainDTO, StatusRequestNewsDTO.class);
        statusDTO.setIsManager(true);

        return statusDTO;
    }

    private void checkIsUserManager(String id) {
        UserEntity userEntity = userRepository.findById(UUID.fromString(id)).orElseThrow(() -> new UserNotFoundById(id));
        List<String> roles = userEntity.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toList());

        if (!roles.contains(RoleEnum.MANAGER.getName())) {
            throw new UserIsNotManagerException(userEntity.getId().toString());
        }
    }
}
