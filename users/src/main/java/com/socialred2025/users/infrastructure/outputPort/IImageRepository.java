package com.socialred2025.users.infrastructure.outputPort;

import java.util.Optional;

import com.socialred2025.users.domain.Image;

public interface IImageRepository {

    Image saveImage(Image image);

    Optional<Image> findImageById(Long id);
}
