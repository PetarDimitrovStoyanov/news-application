package com.newsApplicationMicroservice.userMicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRoleDTO {
    
    @NotNull(message = "The oldRole cannot be null.")
    private OldRoleDTO oldRole;

    @NotNull(message = "The newRole cannot be null.")
    private NewRoleDTO newRole;
}
