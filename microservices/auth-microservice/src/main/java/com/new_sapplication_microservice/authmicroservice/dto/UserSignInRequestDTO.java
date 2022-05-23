package com.new_sapplication_microservice.authmicroservice.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignInRequestDTO {

    @NotNull(message = "The user's email field cannot be null.")
    private String userEmail;

    @NotNull(message = "The password cannot be null.")
    private String password;
}
