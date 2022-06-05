package com.newsAapplicationMicroservice.authmicroservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DeleteRequestMainMicroserviceDTO {

    @JsonProperty("projectId")
    @NotBlank(message = "The field projectId cannot be null or empty.")
    private String id;

    @NotBlank(message = "The field currentUserId cannot be null or empty.")
    private String currentUserId;
}
