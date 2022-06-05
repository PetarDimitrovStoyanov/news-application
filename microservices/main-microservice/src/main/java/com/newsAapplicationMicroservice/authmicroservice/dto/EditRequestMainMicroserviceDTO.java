package com.newsAapplicationMicroservice.authmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EditRequestMainMicroserviceDTO {
    @NotBlank(message = "The field currentUserId cannot be null or empty.")
    private String id;

    private String description;

    private String topic;

    private String mainPicture;

    private String secondPicture;

    private String thirdPicture;

    private String forthPicture;

    private List<CategoryDTO> categories;

    @NotBlank(message = "The field currentUserId cannot be null or empty.")
    private String authorId;

    @NotBlank(message = "The field currentUserId cannot be null or empty.")
    private String currentUserId;
}
