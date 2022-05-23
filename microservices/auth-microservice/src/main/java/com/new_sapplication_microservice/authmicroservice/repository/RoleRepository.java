package com.new_sapplication_microservice.authmicroservice.repository;

import com.new_sapplication_microservice.authmicroservice.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    RoleEntity findByName(String name);
}
