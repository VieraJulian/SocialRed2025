package com.socialred2025.identity.application.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(@NotBlank String username, @NotBlank String password) {
}
