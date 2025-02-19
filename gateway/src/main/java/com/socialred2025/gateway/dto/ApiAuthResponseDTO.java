package com.socialred2025.gateway.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiAuthResponseDTO {

    private boolean success;
    private TokenValidationResultDTO data;
    private String error;
}
