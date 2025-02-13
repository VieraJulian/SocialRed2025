package com.socialred2025.identity.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

    private Long id;
    private RoleType roleName;
    private Set<PermissionDTO> permissions;
}
