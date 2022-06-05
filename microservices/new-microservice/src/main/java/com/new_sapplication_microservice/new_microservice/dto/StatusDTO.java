package com.new_sapplication_microservice.new_microservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StatusDTO {
    @NotNull(message = "The field isActive cannot be null.")
    private Boolean isActive;

    @NotBlank(message = "The field projectId cannot be null or empty.")
    @JsonProperty("projectId")
    private String id;

    @NotNull(message = "The field isManager cannot be null.")
    private Boolean isManager;
}
