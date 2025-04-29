package com.socialred2025.publications.infrastructure.outputadapter;

import com.socialred2025.publications.domain.Comment;
import com.socialred2025.publications.infrastructure.outputport.ICommentRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommentRepositoryPostgresSQL implements ICommentRepository {

    private final ICommentCrudRepositoryPostgresSQL crudRepositoryPostgresSQL;

    public CommentRepositoryPostgresSQL (ICommentCrudRepositoryPostgresSQL crudRepositoryPostgresSQL) {
        this.crudRepositoryPostgresSQL = crudRepositoryPostgresSQL;
    }

    @Override
    public Comment saveComment(Comment comment) {
        return crudRepositoryPostgresSQL.save(comment);
    }

    @Override
    public Optional<Comment> findByUserIdAndPublicationId(Long userId, Long publicationId) {
        return crudRepositoryPostgresSQL.findByUserIdAndPublicationId(userId, publicationId);
    }

    @Override
    public void deleteComment(Long id) {
        crudRepositoryPostgresSQL.deleteById(id);
    }

    @Override
    public boolean existsCommentById(Long id) {
        return crudRepositoryPostgresSQL.existsById(id);
    }

    @Override
    public long countByUserIdAndPublicationId(Long userId, Long publicationId) {
        return crudRepositoryPostgresSQL.countByUserIdAndPublicationId(userId, publicationId);
    }
}
