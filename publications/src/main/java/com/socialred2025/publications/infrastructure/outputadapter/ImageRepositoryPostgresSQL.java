package com.socialred2025.publications.infrastructure.outputadapter;

import com.socialred2025.publications.domain.Image;
import com.socialred2025.publications.infrastructure.outputport.IImageRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ImageRepositoryPostgresSQL implements IImageRepository {

    private final IImageCrudRepositoryPostgresSQL crudRepositoryPostgresSQL;

    public ImageRepositoryPostgresSQL(IImageCrudRepositoryPostgresSQL crudRepositoryPostgresSQL) {
        this.crudRepositoryPostgresSQL = crudRepositoryPostgresSQL;
    }

    @Override
    public Image saveImage(Image image) {
        return crudRepositoryPostgresSQL.save(image);
    }

    @Override
    public Optional<Image> findImageById(Long id) {
        return crudRepositoryPostgresSQL.findById(id);
    }

}
