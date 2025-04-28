package com.socialred2025.publications.infrastructure.outputport;
import com.socialred2025.publications.domain.Like;

public interface ILikeRepository {

    Like saveLike(Like like);
    boolean findLikeByUserIdAndPublicationId(Long userId, Long publicationId);
    void deleteLike(Long id);
}
