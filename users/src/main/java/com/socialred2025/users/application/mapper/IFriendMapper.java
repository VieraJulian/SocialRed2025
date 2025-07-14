package com.socialred2025.users.application.mapper;

import com.socialred2025.users.application.dto.FriendDTO;
import com.socialred2025.users.domain.Friend;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IFriendMapper {
    IFriendMapper INSTANCE = Mappers.getMapper(IFriendMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "userFriend.id", target = "userFriendId")
    FriendDTO friendToFriendDto(Friend friend);
}
