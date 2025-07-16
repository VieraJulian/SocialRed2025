package com.socialred2025.publications.infrastructure.outputadapter;

import com.socialred2025.publications.domain.Publication;
import com.socialred2025.publications.infrastructure.outputport.IPublicationRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PublicationRepositoryPostgresSQL implements IPublicationRepository {

    private final IPublicationCRUDRepositoryPostgresSQL iPublicationCRUDRepositoryPostgresSQL;

    public PublicationRepositoryPostgresSQL(IPublicationCRUDRepositoryPostgresSQL iPublicationCRUDRepositoryPostgresSQL) {
        this.iPublicationCRUDRepositoryPostgresSQL = iPublicationCRUDRepositoryPostgresSQL;
    }

    @Override
    public Publication savePublication(Publication publication) {
        return iPublicationCRUDRepositoryPostgresSQL.save(publication);
    }

    @Override
    public Optional<Publication> findPublicationById(Long id) {
        return iPublicationCRUDRepositoryPostgresSQL.findById(id);
    }

    @Override
    public List<Publication> findAllPublications(List<Long> userIds, Pageable pageable) {
        return iPublicationCRUDRepositoryPostgresSQL.findAllByUserIdInOrderByCreatedAtDesc(userIds, pageable);
    }

    @Override
    public void deletePublicationById(Long id) {
        iPublicationCRUDRepositoryPostgresSQL.deleteById(id);
    }

    @Override
    public boolean existsPublicationById(Long id) {
        return iPublicationCRUDRepositoryPostgresSQL.existsById(id);
    }
}
