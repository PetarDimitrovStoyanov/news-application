package com.newsApplicationMicroservice.userMicroservice.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {

    private String id;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String firstName;

    @NotBlank
    @Size(min = 2)
    private String lastName;

    private String picture;

    @NotBlank
    @Size(min = 4)
    private String password;

    private List<RoleDTO> roles;
}
