package com.socialred2025.publications.infrastructure.outputadapter;

import com.socialred2025.publications.domain.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface IPublicationCRUDRepositoryPostgresSQL extends JpaRepository<Publication, Long> {

    @Query(value = "SELECT p FROM publications p")
    public List<Publication> findAllPublications(Pageable pageable);
}
