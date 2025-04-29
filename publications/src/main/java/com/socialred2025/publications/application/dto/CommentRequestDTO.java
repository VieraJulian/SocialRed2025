package com.socialred2025.publications.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDTO {

    @Size(max = 250, message = "Comment description cannot exceed 250 characters")
    @NotBlank(message = "Description is required")
    private String commentDescription;

    @NotNull
    Long publicationId;
}
