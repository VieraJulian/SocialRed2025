package com.socialred2025.users.infrastructure.outputAdapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.socialred2025.users.domain.Image;
import com.socialred2025.users.infrastructure.outputPort.IImageRepository;

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
