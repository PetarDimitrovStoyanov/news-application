package com.newsAapplicationMicroservice.authmicroservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusRequestMainDTO {
    @NotNull(message = "The field isActive cannot be null.")
    private Boolean isActive;

    @NotBlank(message = "The field projectId cannot be null or empty.")
    @JsonProperty("projectId")
    private String id;

    @NotNull(message = "The field isManager cannot be null.")
    private String currentUserId;
}
