package com.socialred2025.users.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * The RoleDTO class is a Java class with Lombok annotations for getter, setter,
 * and constructors,
 * containing fields for id and name.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InternalRoleDTO {

    private Long id;
    private String roleName;
    private Set<InternalPermissionDTO> permissions;
}
