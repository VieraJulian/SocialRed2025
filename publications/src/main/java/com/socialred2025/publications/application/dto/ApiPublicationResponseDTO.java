package com.socialred2025.publications.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiPublicationResponseDTO<T> {

    private boolean success;
    private T data;
    private String error;
}
