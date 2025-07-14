package com.socialred2025.users.infrastructure.outputadapter;

import com.socialred2025.users.domain.Friend;
import com.socialred2025.users.infrastructure.outputport.IFriendRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FriendRepositoryPostgresSQL implements IFriendRepository {
    private final IFriendCrudRepositoryPostgresSQL friendCrudRepositoryPostgresSQL;

    public FriendRepositoryPostgresSQL(IFriendCrudRepositoryPostgresSQL friendCrudRepositoryPostgresSQL){
        this.friendCrudRepositoryPostgresSQL = friendCrudRepositoryPostgresSQL;
    }


    @Override
    public Friend saveFriend(Friend friend) {
        return friendCrudRepositoryPostgresSQL.save(friend);
    }

    @Override
    public List<Friend> findByUserId(Long userId) {
        return friendCrudRepositoryPostgresSQL.findByUserId(userId);
    }

    @Override
    public Optional<Friend> findByUserIdAndUserFriendId(Long userId, Long userFriendId) {
        return friendCrudRepositoryPostgresSQL.findByUserIdAndUserFriendId(userId, userFriendId);
    }

    @Override
    public void deleteFriendById(Long id) {
        friendCrudRepositoryPostgresSQL.deleteById(id);
    }
}
