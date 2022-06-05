package com.newsAapplicationMicroservice.authmicroservice.controller;

import com.newsAapplicationMicroservice.authmicroservice.dto.LoginRequestDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.LoginResponseDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.UserRegisterRequestDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.UserRegisterResponseDTO;
import com.newsAapplicationMicroservice.authmicroservice.service.AuthService;
import com.newsAapplicationMicroservice.authmicroservice.util.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(Api.AUTH)
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = Api.REGISTER)
    public ResponseEntity<UserRegisterResponseDTO> register(@RequestBody @Valid UserRegisterRequestDTO userRegisterDTO) {
        log.info("The endpoint for user registration was reached successfully.");
        UserRegisterResponseDTO userRegisterResponseDTO = authService.handleRegister(userRegisterDTO);

        return new ResponseEntity<>(userRegisterResponseDTO, HttpStatus.CREATED);
    }

    @PostMapping(value = Api.LOGOUT)
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        log.info("The endpoint for user logout was reached successfully.");
        authService.handleLogout(request, response);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = Api.LOGIN)
    public ResponseEntity<LoginResponseDTO> signIn(@RequestBody @Valid LoginRequestDTO request) throws Exception {
        log.info("The endpoint for user login was reached successfully.");
        LoginResponseDTO responseDTO = authService.handleLogin(request);

        return ResponseEntity.ok().body(responseDTO);
    }
}
