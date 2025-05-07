package com.socialred2025.users.application.mapper;

import com.socialred2025.users.application.dto.FollowDTO;
import com.socialred2025.users.application.dto.InternalPermissionDTO;
import com.socialred2025.users.domain.Follow;
import com.socialred2025.users.domain.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IFollowMapper {

    IFollowMapper INSTANCE = Mappers.getMapper(IFollowMapper.class);

    @Mapping(source = "follower.id", target = "followerId")
    @Mapping(source = "followed.id", target = "followedId")
    FollowDTO followToFollowDto(Follow follow);
}
