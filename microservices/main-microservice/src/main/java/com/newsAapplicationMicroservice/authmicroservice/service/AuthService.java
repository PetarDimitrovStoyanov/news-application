package com.newsAapplicationMicroservice.authmicroservice.service;

import com.newsAapplicationMicroservice.authmicroservice.dto.LoginRequestDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.LoginResponseDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.UserRegisterRequestDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.UserRegisterResponseDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {

    UserRegisterResponseDTO handleRegister(UserRegisterRequestDTO userRegisterDTO);

    void handleLogout(HttpServletRequest request, HttpServletResponse response);

    LoginResponseDTO handleLogin(LoginRequestDTO request) throws Exception;
}
