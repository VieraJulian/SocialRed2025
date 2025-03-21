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
public class PublicationRequestDTO {

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotBlank
    @Size(max = 300, message = "Description cannot exceed 300 characters")
    private String description;
}
