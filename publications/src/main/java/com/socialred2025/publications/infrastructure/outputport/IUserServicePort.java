package com.socialred2025.publications.infrastructure.outputport;

import com.socialred2025.publications.application.dto.FriendDTO;

import java.util.List;

public interface IUserServicePort {

    Boolean existsUserById(Long userId);
    List<FriendDTO> getAllFriends(Long userId, String status);
}
