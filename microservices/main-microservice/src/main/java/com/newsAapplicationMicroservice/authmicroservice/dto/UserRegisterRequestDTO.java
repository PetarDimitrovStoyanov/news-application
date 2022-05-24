package com.newsAapplicationMicroservice.authmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequestDTO {
    @NotBlank(message = "The email cannot be null or empty.")
    @Email(message = "The email is not well formatted.")
    private String email;

    @NotBlank(message = "The password cannot be null or empty.")
    private String password;

    @Size(max = 100, message = "The first name cannot be more than 100 symbols.")
    private String firstName;

    @Size(max = 100, message = "The last name cannot be more than 100 symbols.")
    private String lastName;
}
