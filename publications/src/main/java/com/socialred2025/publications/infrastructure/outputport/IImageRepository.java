package com.socialred2025.publications.infrastructure.outputport;

import com.socialred2025.publications.domain.Image;

import java.util.Optional;

public interface IImageRepository {

    Image saveImage(Image image);

    Optional<Image> findImageById(Long id);
}
