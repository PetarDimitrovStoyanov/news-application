package com.newsAapplicationMicroservice.authmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateNewDTO {
    @NotBlank(message = "The description cannot be null or empty.")
    private String description;

    @NotBlank(message = "The topic cannot be null or empty.")
    private String topic;

    @NotNull(message = "The categories cannot be null.")
    private List<String> categories;

    private String mainPicture;

    private String secondPicture;

    private String thirdPicture;

    private String forthPicture;

    @NotBlank(message = "The authorId cannot be null or empty.")
    private String authorId;
}
