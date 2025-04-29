package com.socialred2025.publications.infrastructure.outputadapter;

import com.socialred2025.publications.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICommentCrudRepositoryPostgresSQL extends JpaRepository<Comment, Long> {

    Optional<Comment> findByUserIdAndPublicationId(Long userId, Long publicationId);
    long countByUserIdAndPublicationId(long userId, Long publicationId);
}
