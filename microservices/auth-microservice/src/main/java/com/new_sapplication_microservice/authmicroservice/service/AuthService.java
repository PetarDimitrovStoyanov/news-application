package com.new_sapplication_microservice.authmicroservice.service;

import com.new_sapplication_microservice.authmicroservice.dto.UserRegisterRequestDTO;
import com.new_sapplication_microservice.authmicroservice.dto.UserRegisterResponseDTO;
import com.new_sapplication_microservice.authmicroservice.dto.UserSignInRequestDTO;
import com.new_sapplication_microservice.authmicroservice.dto.UserSignInResponseDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {
    UserSignInResponseDTO handleSignIn(UserSignInRequestDTO userSignInRequestDTO);

    UserRegisterResponseDTO handleRegister(UserRegisterRequestDTO userRegisterDTO);

    void handleLogout(HttpServletRequest request, HttpServletResponse response);

}
