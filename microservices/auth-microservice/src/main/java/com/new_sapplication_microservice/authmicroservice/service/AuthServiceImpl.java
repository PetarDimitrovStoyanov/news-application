package com.new_sapplication_microservice.authmicroservice.service;

import com.new_sapplication_microservice.authmicroservice.dto.UserRegisterRequestDTO;
import com.new_sapplication_microservice.authmicroservice.dto.UserRegisterResponseDTO;
import com.new_sapplication_microservice.authmicroservice.dto.UserSignInRequestDTO;
import com.new_sapplication_microservice.authmicroservice.dto.UserSignInResponseDTO;
import com.new_sapplication_microservice.authmicroservice.entity.RoleEntity;
import com.new_sapplication_microservice.authmicroservice.entity.UserEntity;
import com.new_sapplication_microservice.authmicroservice.enums.RoleEnum;
import com.new_sapplication_microservice.authmicroservice.exception.UserAlreadyExistsException;
import com.new_sapplication_microservice.authmicroservice.exception.UserNotFoundException;
import com.new_sapplication_microservice.authmicroservice.repository.RoleRepository;
import com.new_sapplication_microservice.authmicroservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final UserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    private final RoleRepository roleRepository;

    @Override
    public UserSignInResponseDTO handleSignIn(UserSignInRequestDTO userSignInRequestDTO) {
        UserEntity userEntity = findUserByEmailAndPassword(userSignInRequestDTO);
        UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getEmail());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userSignInRequestDTO.getUserEmail(),
                userSignInRequestDTO.getPassword(),
                userDetails.getAuthorities()
        );

        final Authentication userAuthenticator = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(userAuthenticator);

        return modelMapper.map(userEntity, UserSignInResponseDTO.class);
    }

    private UserEntity findUserByEmailAndPassword(UserSignInRequestDTO userSignInRequestDTO) {
        String encodedPassword = passwordEncoder.encode(userSignInRequestDTO.getPassword());

        return userRepository
                .findAllByEmailAndPassword(userSignInRequestDTO.getUserEmail(), encodedPassword)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserRegisterResponseDTO handleRegister(UserRegisterRequestDTO userRegisterDTO) {
        UserEntity userEntity = userRepository.findByEmail(userRegisterDTO.getEmail()).orElse(null);

        if (userEntity != null) {
            throw new UserAlreadyExistsException(userRegisterDTO.getEmail());
        }

        UserEntity newUser = createUserEntity(userRegisterDTO);
        UserEntity savedNewUser = userRepository.save(newUser);

        return modelMapper.map(savedNewUser, UserRegisterResponseDTO.class);
    }

    private UserEntity createUserEntity(UserRegisterRequestDTO userRegisterDTO) {
        UserEntity newUser = modelMapper.map(userRegisterDTO, UserEntity.class);
        RoleEntity roleEntity = roleRepository.findByName(RoleEnum.CREATOR.getName());
        newUser.setRoles(List.of(roleEntity));
        newUser.setPicture(null);

        return newUser;
    }

    @Override
    public void handleLogout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.getContext().setAuthentication(null);
        SecurityContextHolder.clearContext();

        SecurityContextLogoutHandler context = new SecurityContextLogoutHandler();
        context.logout(request, response, null);
    }
}
