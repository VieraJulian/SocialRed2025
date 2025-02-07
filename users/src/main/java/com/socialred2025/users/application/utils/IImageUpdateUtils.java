package com.socialred2025.users.application.utils;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.socialred2025.users.domain.Image;
import com.socialred2025.users.domain.UserEntity;
import com.socialred2025.users.infrastructure.outputPort.IImageRepository;
import com.socialred2025.users.infrastructure.utils.ImageUtils;

/**
 * The `IImageUpdateUtils` class in Java is responsible for updating user images
 * by uploading a new
 * image file and saving it to the repository.
 */
@Component
public class IImageUpdateUtils {

    private final IImageRepository imageRepository;

    private final ImageUtils imageUtils;

    public IImageUpdateUtils(IImageRepository imageRepository, ImageUtils imageUtils) {
        this.imageRepository = imageRepository;
        this.imageUtils = imageUtils;
    }

    public void updateImage(UserEntity user, MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            Image newImage = imageUtils.fileUpload(file);
            Image oldImage = user.getImage();
            Image saveUserImage = null;

            if (oldImage != null) {
                Optional<Image> imageDB = imageRepository.findImageById(oldImage.getId());

                if (imageDB.isPresent()) {
                    imageDB.get().setImageUrl(newImage.getImageUrl());

                    saveUserImage = imageRepository.saveImage(imageDB.get());
                }

            } else {
                Image newUserImage = Image.builder()
                        .imageUrl(newImage.getImageUrl())
                        .build();

                saveUserImage = imageRepository.saveImage(newUserImage);
            }

            user.setImage(saveUserImage);

        }
    }

}
