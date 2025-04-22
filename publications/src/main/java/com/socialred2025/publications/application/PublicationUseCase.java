package com.socialred2025.publications.application;

import com.socialred2025.publications.application.dto.PublicationRequestDTO;
import com.socialred2025.publications.application.dto.PublicationResponseDTO;
import com.socialred2025.publications.application.dto.PublicationUpdateRequestDTO;
import com.socialred2025.publications.application.exception.ImageNotFoundException;
import com.socialred2025.publications.application.exception.PublicationNotFoundException;
import com.socialred2025.publications.application.exception.UnauthorizedActionException;
import com.socialred2025.publications.application.exception.UserNotFoundException;
import com.socialred2025.publications.application.mapper.IPublicationMapper;
import com.socialred2025.publications.domain.Image;
import com.socialred2025.publications.domain.Publication;
import com.socialred2025.publications.infrastructure.inputport.IPublicationInputPort;
import com.socialred2025.publications.infrastructure.outputport.IImageRepository;
import com.socialred2025.publications.infrastructure.outputport.IPublicationRepository;
import com.socialred2025.publications.infrastructure.utils.ImageUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PublicationUseCase implements IPublicationInputPort {

    private final IPublicationMapper iPublicationMapper;

    private final IPublicationRepository iPublicationRepository;

    private final IImageRepository iImageRepository;

    private final ImageUtils imageUtils;

    public PublicationUseCase(IPublicationMapper iPublicationMapper, ImageUtils imageUtils, IPublicationRepository iPublicationRepository, IImageRepository iImageRepository) {
        this.imageUtils = imageUtils;
        this.iPublicationRepository = iPublicationRepository;
        this.iPublicationMapper = iPublicationMapper;
        this.iImageRepository = iImageRepository;
    }

    @Override
    public PublicationResponseDTO createPublication(Long userId, PublicationRequestDTO publicationRequestDTO, MultipartFile file) throws UserNotFoundException, ImageNotFoundException, IOException {
        Publication publication = iPublicationMapper.publicationRequestDtoToPublication(publicationRequestDTO);

        if (file == null || file.isEmpty()) {
            throw new ImageNotFoundException("Image is required to create a publication.");
        }

        publication.setUserId(userId);

        Image image = iImageRepository.saveImage(imageUtils.fileUpload(file));

        publication.setImage(image);

        return iPublicationMapper.publicationToPublicationResponseDto(iPublicationRepository.savePublication(publication));
    }

    @Override
    public PublicationResponseDTO updatePublication(Long userId, Long id, PublicationUpdateRequestDTO publicationUpdateRequestDTO, MultipartFile file) throws PublicationNotFoundException, UnauthorizedActionException, IOException {
        Publication publication = iPublicationRepository.findPublicationById(id).orElseThrow(() -> new PublicationNotFoundException("Publication not found with id: " + id));

        if (!publication.getUserId().equals(userId)) {
            throw new UnauthorizedActionException("User is not authorized to edit this publication.");
        }

        if (file != null && !file.isEmpty()) {
            String newImageUrl = imageUtils.fileUpload(file).getImageUrl();
            publication.getImage().setImageUrl(newImageUrl);
        }

        publication.setDescription(publicationUpdateRequestDTO.getDescription());
        publication.setEdited(true);

        return iPublicationMapper.publicationToPublicationResponseDto(iPublicationRepository.savePublication(publication));
    }

    @Override
    public PublicationResponseDTO findPublication(Long id) {
        return null;
    }

    @Override
    public List<PublicationResponseDTO> feed(Long userId, int page, int size) {
        return List.of();
    }

    @Override
    public String deletePublication(Long id) {
        return "";
    }
}
