package com.socialred2025.users.infrastructure.outputport;

import com.socialred2025.users.domain.Follow;

import java.util.List;
import java.util.Optional;

public interface IFollowRepository {

    Follow saveFollow(Follow follow);
    List<Follow> findByFollowerId(Long followerId);
    Optional<Follow> findByFollowerIdAndFollowedId(Long followerId, Long followedId);
    void deleteFollowById(Long id);
}
