package com.newsAapplicationMicroservice.authmicroservice.databaseInit;

import com.newsAapplicationMicroservice.authmicroservice.entity.RoleEntity;
import com.newsAapplicationMicroservice.authmicroservice.entity.UserEntity;
import com.newsAapplicationMicroservice.authmicroservice.enums.RoleEnum;
import com.newsAapplicationMicroservice.authmicroservice.repository.RoleRepository;
import com.newsAapplicationMicroservice.authmicroservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class Initializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        RoleEntity candidateRole = new RoleEntity();
        candidateRole.setName(RoleEnum.CANDIDATE.getName());

        RoleEntity creatorRole = new RoleEntity();
        creatorRole.setName(RoleEnum.CREATOR.getName());

        RoleEntity managerRole = new RoleEntity();
        managerRole.setName(RoleEnum.MANAGER.getName());

        RoleEntity adminRole = new RoleEntity();
        adminRole.setName(RoleEnum.ADMIN.getName());

        if (roleRepository.findAll().isEmpty()) {
            roleRepository.saveAllAndFlush(List.of(candidateRole, creatorRole, managerRole, adminRole));

            log.info("Successfully created roles: admin, manager, creator, candidate");
        }

        if (userRepository.findAll().isEmpty()) {
            UserEntity admin = new UserEntity();
            admin.setEmail("admin@abv.bg");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRoles(List.of(creatorRole, managerRole, adminRole));
            admin.setFirstName("Admin");
            admin.setLastName("Adminov");
            admin.setPicture(null);
            userRepository.saveAndFlush(admin);

            UserEntity candidate = new UserEntity();
            candidate.setEmail("candidate@abv.bg");
            candidate.setPassword(passwordEncoder.encode("candidate"));
            candidate.setRoles(List.of(candidateRole));
            candidate.setFirstName("Candidate");
            candidate.setLastName("Candidatov");
            candidate.setPicture(null);
            userRepository.saveAndFlush(candidate);

            UserEntity creator = new UserEntity();
            creator.setEmail("creator@abv.bg");
            creator.setPassword(passwordEncoder.encode("creator"));
            creator.setRoles(List.of(creatorRole));
            creator.setFirstName("Creator");
            creator.setLastName("Creatorov");
            creator.setPicture(null);
            userRepository.saveAndFlush(creator);

            UserEntity manager = new UserEntity();
            manager.setEmail("manager@abv.bg");
            manager.setPassword(passwordEncoder.encode("manager"));
            manager.setRoles(List.of(managerRole));
            manager.setFirstName("Manager");
            manager.setLastName("Managerov");
            manager.setPicture(null);
            userRepository.saveAndFlush(manager);

            log.info("Successfully created users: admin, manager, creator, candidate");
        }
    }
}
