package com.new_sapplication_microservice.authmicroservice.controller;

import com.new_sapplication_microservice.authmicroservice.dto.UserRegisterRequestDTO;
import com.new_sapplication_microservice.authmicroservice.dto.UserRegisterResponseDTO;
import com.new_sapplication_microservice.authmicroservice.dto.UserSignInRequestDTO;
import com.new_sapplication_microservice.authmicroservice.dto.UserSignInResponseDTO;
import com.new_sapplication_microservice.authmicroservice.service.AuthService;
import com.new_sapplication_microservice.authmicroservice.util.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @GetMapping(value = "/admin")
    public String admin() {
        //TODO: to be removed - test method
        return "admin";
    }

    @GetMapping(value = "/creator")
    public String creator() {
        //TODO: to be removed - test method
        return "creator";
    }

    @GetMapping(value = "/manager")
    public String manager() {
        //TODO: to be removed - test method
        return "manager";
    }

    @GetMapping(value = "/free")
    public String free() {
        //TODO: to be removed - test method
        return "free";
    }

    @PostMapping(value = Api.SIGN_IN)
    public ResponseEntity<UserSignInResponseDTO> signIn(@RequestBody @Valid UserSignInRequestDTO userSignInRequestDTO) {
        UserSignInResponseDTO userSignInResponseDTO = authService.handleSignIn(userSignInRequestDTO);

        return new ResponseEntity<>(userSignInResponseDTO, HttpStatus.OK);
    }

    @PostMapping(value = Api.REGISTER)
    public ResponseEntity<UserRegisterResponseDTO> register(@RequestBody @Valid UserRegisterRequestDTO userRegisterDTO) {
        UserRegisterResponseDTO userRegisterResponseDTO = authService.handleRegister(userRegisterDTO);

        return new ResponseEntity<>(userRegisterResponseDTO, HttpStatus.CREATED);
    }

    @PostMapping(value = Api.LOGOUT)
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        authService.handleLogout(request, response);
        //TODO: to be checked whether works
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
