package com.new_sapplication_microservice.authmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignInResponseDTO {
    private String id;

    private String firstName;

    private String lastName;

    private List<RoleDTO> roles;

    private String email;

    private String picture;
}
