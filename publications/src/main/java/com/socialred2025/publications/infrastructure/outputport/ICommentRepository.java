package com.socialred2025.publications.infrastructure.outputport;

import com.socialred2025.publications.domain.Comment;

import java.util.Optional;

public interface ICommentRepository {

    Comment saveComment(Comment comment);
    Optional<Comment> findByUserIdAndPublicationId(Long userId, Long publicationId);
    void deleteComment(Long id);
    boolean existsCommentById(Long id);
    long countByUserIdAndPublicationId(Long userId, Long publicationId);
}
