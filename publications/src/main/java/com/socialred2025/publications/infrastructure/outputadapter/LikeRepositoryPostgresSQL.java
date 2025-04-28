package com.socialred2025.publications.infrastructure.outputadapter;

import com.socialred2025.publications.domain.Like;
import com.socialred2025.publications.infrastructure.outputport.ILikeRepository;
import org.springframework.stereotype.Component;

@Component
public class LikeRepositoryPostgresSQL implements ILikeRepository {

    private final ILikeCrudRepositoryPostgresSQL iLikeCrudRepositoryPostgresSQL;

    public LikeRepositoryPostgresSQL(ILikeCrudRepositoryPostgresSQL iLikeCrudRepositoryPostgresSQL) {
        this.iLikeCrudRepositoryPostgresSQL = iLikeCrudRepositoryPostgresSQL;
    }

    @Override
    public Like saveLike(Like like) {
        return iLikeCrudRepositoryPostgresSQL.save(like);
    }

    @Override
    public boolean findLikeByUserIdAndPublicationId(Long userId, Long publicationId){
        return iLikeCrudRepositoryPostgresSQL.existsByUserIdAndPublicationId(userId, publicationId);
    }

    @Override
    public void deleteLike(Long id) {
        iLikeCrudRepositoryPostgresSQL.deleteById(id);
    }
}
