package com.socialred2025.publications.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private Long id;
    private String commentDescription;
    private Long userId;
    private Long publicationId;
}
