package com.socialred2025.users.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.socialred2025.users.application.dto.UserCreateRequestDto;
import com.socialred2025.users.application.dto.UserResponseDTO;
import com.socialred2025.users.domain.UserEntity;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    @Mapping(target = "enabled", constant = "true")
    @Mapping(target = "accountNotExpired", constant = "true")
    @Mapping(target = "accountNotLocked", constant = "true")
    @Mapping(target = "credentialNotExpired", constant = "true")
    UserEntity userCreateRequestDtoToUserEntity(UserCreateRequestDto createRequestDto);

    UserResponseDTO userEntityToUserResponseDto(UserEntity userEntity);

}
