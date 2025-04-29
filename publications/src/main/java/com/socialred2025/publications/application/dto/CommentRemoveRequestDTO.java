package com.socialred2025.publications.application.dto;

import jakarta.validation.constraints.NotNull;

public class CommentRemoveRequestDTO {

    @NotNull
    Long publicationId;
}
