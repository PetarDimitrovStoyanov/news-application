package com.new_sapplication_microservice.new_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class EditDTO {
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

    @NotNull(message = "The field isManager cannot be null.")
    private Boolean isManager;
}
