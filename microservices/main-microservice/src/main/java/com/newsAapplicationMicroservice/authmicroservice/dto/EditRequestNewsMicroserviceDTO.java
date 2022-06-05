package com.newsAapplicationMicroservice.authmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class EditRequestNewsMicroserviceDTO {
    @NotNull(message = "The field id cannot be null.")
    private String id;

    private String description;

    private String topic;

    private String mainPicture;

    private String secondPicture;

    private String thirdPicture;

    private String forthPicture;

    private List<CategoryDTO> categories;

    @NotNull(message = "The field authorId cannot be null.")
    private String authorId;

    @NotNull(message = "The field isAdmin cannot be null.")
    private Boolean isManager;
}
