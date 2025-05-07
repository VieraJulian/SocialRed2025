package com.socialred2025.users.infrastructure.outputadapter;

import com.socialred2025.users.domain.Follow;
import com.socialred2025.users.infrastructure.outputport.IFollowRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FollowRepositoryPostgresSQL implements IFollowRepository {

    private final IFollowCrudRepositoryPostgresSQL followCrudRepositoryPostgresSQL;

    public FollowRepositoryPostgresSQL(IFollowCrudRepositoryPostgresSQL followCrudRepositoryPostgresSQL) {
        this.followCrudRepositoryPostgresSQL = followCrudRepositoryPostgresSQL;
    }

    @Override
    public Follow saveFollow(Follow follow) {
        return followCrudRepositoryPostgresSQL.save(follow);
    }

    @Override
    public List<Follow> findByFollowerId(Long followerId) {
        return followCrudRepositoryPostgresSQL.findByFollowerId(followerId);
    }

    @Override
    public Optional<Follow> findByFollowerIdAndFollowedId(Long followerId, Long followedId) {
        return followCrudRepositoryPostgresSQL.findByFollowerIdAndFollowedId(followerId, followedId);
    }

    @Override
    public void deleteFollowById(Long id) {
        followCrudRepositoryPostgresSQL.deleteById(id);
    }
}
