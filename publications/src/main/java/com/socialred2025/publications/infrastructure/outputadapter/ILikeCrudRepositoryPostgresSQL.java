package com.socialred2025.publications.infrastructure.outputadapter;

import com.socialred2025.publications.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILikeCrudRepositoryPostgresSQL extends JpaRepository<Like, Long> {

    boolean existsByUserIdAndPublicationId(Long userId, Long publicationId);

    Optional<Like> findByUserIdAndPublicationId(Long userId, Long publicationId);
}
