package com.socialred2025.identity.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiAuthResponseDTO<T> {

    private boolean success;
    private T data;
    private String error;
}
