package com.socialred2025.publications.infrastructure.outputadapter;

import com.socialred2025.publications.domain.Publication;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPublicationCRUDRepositoryPostgresSQL extends JpaRepository<Publication, Long> {

    public List<Publication> findAllByUserIdInOrderByCreatedAtDesc(List<Long> userIds, Pageable pageable);
}
