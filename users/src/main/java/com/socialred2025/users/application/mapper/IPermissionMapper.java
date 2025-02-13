package com.socialred2025.users.application.mapper;

import com.socialred2025.users.application.dto.InternalPermissionDTO;
import com.socialred2025.users.domain.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IPermissionMapper {

    IPermissionMapper INSTANCE = Mappers.getMapper(IPermissionMapper.class);

    @Mapping(source = "permissionName", target = "permissionName")
    InternalPermissionDTO permissionToInternalPermissionDTO(Permission permission);
}
