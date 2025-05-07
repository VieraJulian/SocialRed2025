package com.socialred2025.users.infrastructure.outputadapter;

import com.socialred2025.users.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFollowCrudRepositoryPostgresSQL extends JpaRepository<Follow, Long> {

    List<Follow> findByFollowerId(Long followerId);

    Optional<Follow> findByFollowerIdAndFollowedId(Long followerId, Long followedId);


}
