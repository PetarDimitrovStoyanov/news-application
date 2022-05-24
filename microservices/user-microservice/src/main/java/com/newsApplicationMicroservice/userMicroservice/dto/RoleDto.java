package com.newsApplicationMicroservice.userMicroservice.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private String id;
    private String name;
}
