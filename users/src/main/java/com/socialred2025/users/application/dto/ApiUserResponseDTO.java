package com.socialred2025.users.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
