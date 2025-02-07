package com.socialred2025.users.application.mapper;

import org.mapstruct.Mapper;

import com.socialred2025.users.application.dto.RoleDTO;
import com.socialred2025.users.domain.Role;

// The code snippet is defining a MapStruct mapper interface in Java.
@Mapper(componentModel = "spring")
public interface IRoleMapper {

    RoleDTO roleToRoleDto(Role role);

}
