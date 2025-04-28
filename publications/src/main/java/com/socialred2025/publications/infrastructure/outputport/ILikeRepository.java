package com.socialred2025.publications.infrastructure.outputport;
import com.socialred2025.publications.domain.Like;

import java.util.Optional;

public interface ILikeRepository {

    Like saveLike(Like like);
    Optional<Like> findByUserIdAndPublicationId(Long userId, Long publicationId);
    void deleteLike(Long id);
    boolean existsLikeById(Long id);
}
