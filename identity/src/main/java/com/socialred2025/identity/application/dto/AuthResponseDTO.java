package com.socialred2025.identity.application.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;

@JsonPropertyOrder({"username", "message", "jwt", "status"})
@Builder
public record AuthResponseDTO(String username, String message, String jwt, boolean status) {
}
