package com.socialred2025.users.infrastructure.outputport;

import com.socialred2025.users.domain.Friend;

import java.util.List;
import java.util.Optional;

public interface IFriendRepository {

    Friend saveFriend(Friend friend);
    List<Friend> findByUserId(Long userId);
    Optional<Friend> findByUserIdAndUserFriendId(Long userId, Long userFriendId);
    void deleteFriendById(Long id);
}
