package com.socialred2025.publications.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublicationUpdateRequestDTO {

    @NotBlank
    @Size(max = 300, message = "Description cannot exceed 300 characters")
    private String description;
}
