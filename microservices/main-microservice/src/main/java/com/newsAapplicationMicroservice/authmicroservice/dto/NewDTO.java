package com.newsAapplicationMicroservice.authmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewDTO {
    private String id;

    private String description;

    private String topic;

    private Integer views;

    private LocalDateTime createdDate;

    private LocalDateTime editedDate;

    private Boolean isActive;

    private String mainPicture;

    private String secondPicture;

    private String thirdPicture;

    private String forthPicture;

    private List<CategoryDTO> categories;

    private String authorId;

    private String mangerId;
}
