package com.socialred2025.users.application.mapper;

import com.socialred2025.users.application.dto.InternalRoleDTO;
import org.mapstruct.Mapper;

import com.socialred2025.users.application.dto.RoleDTO;
import com.socialred2025.users.domain.Role;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

// The code snippet is defining a MapStruct mapper interface in Java.
@Mapper(componentModel = "spring", uses = { IPermissionMapper.class })
public interface IRoleMapper {

    IRoleMapper INSTANCE = Mappers.getMapper(IRoleMapper.class);

    @Mapping(source = "roleName", target = "roleName")
    RoleDTO roleToRoleDto(Role role);

    @Mapping(source = "roleName", target = "roleName")
    @Mapping(source = "permissionSet", target = "permissions")
    InternalRoleDTO roleToInternalRoleDTO(Role role);

}
