package com.socialred2025.identity.application.dto;

import lombok.*;

/**
 * The RoleDTO class is a Java class with Lombok annotations for getter, setter,
 * and constructors,
 * containing fields for id and name.
 */
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
