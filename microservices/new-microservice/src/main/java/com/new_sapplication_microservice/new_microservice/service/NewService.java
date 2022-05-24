package com.new_sapplication_microservice.new_microservice.service;

import com.new_sapplication_microservice.new_microservice.dto.CreateANewDTO;
import com.new_sapplication_microservice.new_microservice.dto.NewDTO;
import com.new_sapplication_microservice.new_microservice.entity.NewEntity;

import java.util.List;

public interface NewService {
    List<NewDTO> findAllNews();

    NewDTO findById(String id);

    NewEntity createANew(CreateANewDTO newDto);
}
