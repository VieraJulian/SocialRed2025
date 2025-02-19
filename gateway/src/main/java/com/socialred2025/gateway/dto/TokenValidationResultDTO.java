package com.socialred2025.gateway.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenValidationResultDTO {

    private boolean valid;
    private String username;
    private String authorities;
}
