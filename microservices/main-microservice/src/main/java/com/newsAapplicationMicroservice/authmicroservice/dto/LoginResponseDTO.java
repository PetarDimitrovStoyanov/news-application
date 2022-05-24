package com.newsAapplicationMicroservice.authmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String jwtToken;

    private String picture;

    private List<RoleMainDTO> roles;
}
