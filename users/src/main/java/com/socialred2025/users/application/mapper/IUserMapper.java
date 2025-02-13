package com.socialred2025.users.application.mapper;

import com.socialred2025.users.application.dto.InternalUserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.socialred2025.users.application.dto.UserCreateRequestDTO;
import com.socialred2025.users.application.dto.UserResponseDTO;
import com.socialred2025.users.domain.UserEntity;

// This Java code snippet defines a MapStruct mapper interface named `IUserMapper`.
@Mapper(componentModel = "spring", uses = { IRoleMapper.class })
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    @Mapping(target = "enabled", constant = "true")
    @Mapping(target = "accountNotExpired", constant = "true")
    @Mapping(target = "accountNotLocked", constant = "true")
    @Mapping(target = "credentialNotExpired", constant = "true")
    UserEntity userCreateRequestDtoToUserEntity(UserCreateRequestDTO createRequestDto);

    UserResponseDTO userEntityToUserResponseDto(UserEntity userEntity);

    @Mapping(source = "role", target = "role")
    InternalUserResponseDTO userEntityToInternalUserResponseDTO(UserEntity userEntity);

}
