package com.socialred2025.users.infrastructure.outputadapter;

import com.socialred2025.users.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFriendCrudRepositoryPostgresSQL extends JpaRepository<Friend, Long> {

    List<Friend> findByUserId(Long userId);
    Optional<Friend> findByUserIdAndUserFriendId(Long userId, Long userFriendId);

}
