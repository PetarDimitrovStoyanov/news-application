package com.newsAapplicationMicroservice.authmicroservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DeleteRequestNewsMicroserviceDTO {

    @JsonProperty("projectId")
    @NotBlank(message = "The field projectId cannot be null or empty.")
    private String id;

    @NotNull(message = "The field currentUserId cannot be null.")
    private Boolean isManager;
}
