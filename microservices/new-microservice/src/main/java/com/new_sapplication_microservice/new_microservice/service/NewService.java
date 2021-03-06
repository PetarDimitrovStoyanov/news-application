package com.new_sapplication_microservice.new_microservice.service;

import com.new_sapplication_microservice.new_microservice.dto.CreateANewDTO;
import com.new_sapplication_microservice.new_microservice.dto.DeleteDTO;
import com.new_sapplication_microservice.new_microservice.dto.EditDTO;
import com.new_sapplication_microservice.new_microservice.dto.NewDTO;
import com.new_sapplication_microservice.new_microservice.dto.StatusDTO;
import com.new_sapplication_microservice.new_microservice.entity.NewEntity;

import java.util.List;

public interface NewService {
    List<NewDTO> findAllByIsActiveTrueOrderByCreatedDateDesc();

    NewDTO findById(String id);

    NewEntity createANew(CreateANewDTO newDto);

    List<NewDTO> findAllByCategory(String categoryId);

    List<NewDTO> findAllManagement();

    void addViewToProject(String projectId);

    void deleteById(DeleteDTO deleteDTO);

    void editProject(EditDTO editDTO);

    void changeStatus(StatusDTO statusDTO);
}
