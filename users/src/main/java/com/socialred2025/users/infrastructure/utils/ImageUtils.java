package com.socialred2025.users.infrastructure.utils;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.socialred2025.users.domain.Image;

/**
 * Utilities for image management.
 * Provides methods for uploading image files to a cloud storage service
 * (Cloudinary).
 * Validates the size and format of image files before uploading them.
 */
@Component
public class ImageUtils {

    private final Cloudinary cloudinary;

    public ImageUtils(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public Image fileUpload(MultipartFile file) throws IOException {

        if (file == null || file.isEmpty()) {
            return null;
        }

        validateFile(file);

        String url = uploadToCloudinary(file);

        return Image.builder()
                .imageUrl(url)
                .build();
    }

    private String uploadToCloudinary(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return uploadResult.get("url").toString();
    }

    private void validateFile(MultipartFile file) {

        if (file.getSize() > 5000000) {
            throw new IllegalArgumentException("The image exceeds 5 MB");
        }

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if (!extension.equals("jpg") && !extension.equals("jpeg") && !extension.equals("png")) {
            throw new IllegalArgumentException("The allowed extensions are ‘.jpg’, ‘.jpe’, ‘.png’");
        }
    }
}
