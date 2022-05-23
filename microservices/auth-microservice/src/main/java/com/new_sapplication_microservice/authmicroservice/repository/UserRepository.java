package com.new_sapplication_microservice.authmicroservice.repository;

import com.new_sapplication_microservice.authmicroservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findAllByEmailAndPassword(String email, String password);
}
