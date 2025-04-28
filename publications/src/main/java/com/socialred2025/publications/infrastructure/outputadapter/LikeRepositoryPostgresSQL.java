package com.socialred2025.publications.infrastructure.outputadapter;

import com.socialred2025.publications.domain.Like;
import com.socialred2025.publications.infrastructure.outputport.ILikeRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
    public Optional<Like> findByUserIdAndPublicationId(Long userId, Long publicationId){
        return iLikeCrudRepositoryPostgresSQL.findByUserIdAndPublicationId(userId, publicationId);
    }

    @Override
    public void deleteLike(Long id) {
        iLikeCrudRepositoryPostgresSQL.deleteById(id);
    }

    @Override
    public boolean existsLikeById(Long id) {
        return false;
    }
}
