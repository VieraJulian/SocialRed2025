package com.socialred2025.publications.application.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiUserResponseDTO<T> {

    private boolean success;
    private T data;
    private String error;
}
