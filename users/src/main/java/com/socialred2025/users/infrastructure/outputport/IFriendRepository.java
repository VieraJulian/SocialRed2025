package com.socialred2025.users.infrastructure.outputport;

import com.socialred2025.users.domain.Friend;
import com.socialred2025.users.domain.StatusType;

import java.util.List;
import java.util.Optional;

public interface IFriendRepository {

    Friend saveFriend(Friend friend);
    List<Friend> findByUserId(Long userId);
    List<Friend> findByUserIdAndStatus(Long userId, StatusType status);
    Optional<Friend> findByUserIdAndUserFriendId(Long userId, Long userFriendId);
    void deleteFriendById(Long id);
}
