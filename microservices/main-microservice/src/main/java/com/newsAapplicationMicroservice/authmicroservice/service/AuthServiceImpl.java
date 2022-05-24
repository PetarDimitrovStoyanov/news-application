package com.newsAapplicationMicroservice.authmicroservice.service;

import com.newsAapplicationMicroservice.authmicroservice.dto.LoginRequestDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.LoginResponseDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.UserRegisterRequestDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.UserRegisterResponseDTO;
import com.newsAapplicationMicroservice.authmicroservice.entity.RoleEntity;
import com.newsAapplicationMicroservice.authmicroservice.entity.UserEntity;
import com.newsAapplicationMicroservice.authmicroservice.enums.RoleEnum;
import com.newsAapplicationMicroservice.authmicroservice.exception.UserAlreadyExistsException;
import com.newsAapplicationMicroservice.authmicroservice.exception.UserNotFoundException;
import com.newsAapplicationMicroservice.authmicroservice.repository.RoleRepository;
import com.newsAapplicationMicroservice.authmicroservice.repository.UserRepository;
import com.newsAapplicationMicroservice.authmicroservice.util.token.JwtUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private JwtUtil jwtUtil;

    @Override
    public LoginResponseDTO handleLogin(LoginRequestDTO request) throws Exception {
        UserEntity userEntity = findUserByEmailAndPassword(request);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        try {
            authenticationManager.authenticate(token);
        } catch (Exception e){
            throw new Exception("not valid credentials");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        LoginResponseDTO loginResponseDTO = modelMapper.map(userEntity, LoginResponseDTO.class);
        loginResponseDTO.setJwtToken("Bearer " + jwtUtil.generateToken(userDetails));

        return loginResponseDTO;
    }

    private UserEntity findUserByEmailAndPassword(LoginRequestDTO dto) {
        UserEntity userEntity = userRepository
                .findByEmail(dto.getEmail())
                .orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(dto.getPassword(), userEntity.getPassword())) {
            throw new UserNotFoundException();
        }

        return userEntity;
    }

    @Override
    @Transactional
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
        RoleEntity roleEntity = roleRepository.findByName(RoleEnum.CANDIDATE.getName());
        newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
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
