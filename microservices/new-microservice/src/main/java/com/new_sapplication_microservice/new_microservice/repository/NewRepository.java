package com.new_sapplication_microservice.new_microservice.repository;

import com.new_sapplication_microservice.new_microservice.entity.NewEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NewRepository extends MongoRepository<NewEntity, String> {
    List<NewEntity> findAllByCategoriesId(String categoryId);
}
