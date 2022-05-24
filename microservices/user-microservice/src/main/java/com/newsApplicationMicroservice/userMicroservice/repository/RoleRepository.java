package com.newsApplicationMicroservice.userMicroservice.repository;

import com.newsApplicationMicroservice.userMicroservice.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    RoleEntity findByName(String name);
}
