package com.socialred2025.identity.application.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequestDTO(@NotBlank String username, @NotBlank String password) {
}
