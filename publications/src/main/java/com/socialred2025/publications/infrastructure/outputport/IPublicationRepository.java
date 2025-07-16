package com.socialred2025.publications.infrastructure.outputport;

import com.socialred2025.publications.domain.Publication;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface IPublicationRepository {

    Publication savePublication(Publication publication);
    Optional<Publication> findPublicationById(Long id);
    List<Publication> findAllPublications(List<Long> userIds, Pageable pageable);
    boolean existsPublicationById(Long id);
    void deletePublicationById(Long id);
}
