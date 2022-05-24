package com.newsAapplicationMicroservice.authmicroservice.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {

    @NotNull(message = "The user's email field cannot be null.")
    private String email;

    @NotNull(message = "The password cannot be null.")
    private String password;
}
