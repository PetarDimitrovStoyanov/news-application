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
public class UserMainDTO {
    private String id;

    private String email;

    private String firstName;

    private String lastName;

    private String picture;

    private List<RoleMainDTO> roles;
}
