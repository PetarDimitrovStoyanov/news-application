package com.new_sapplication_microservice.new_microservice.controller;

import com.new_sapplication_microservice.new_microservice.dto.CategoryDTO;
import com.new_sapplication_microservice.new_microservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        log.info("Getting all categories");

        List<CategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok().body(categories);
    }
}
