package com.new_sapplication_microservice.new_microservice.service;

import com.new_sapplication_microservice.new_microservice.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
}
