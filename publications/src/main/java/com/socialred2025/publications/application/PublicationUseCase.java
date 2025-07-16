package com.socialred2025.publications.application;

import com.socialred2025.publications.application.dto.*;
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
import com.socialred2025.publications.infrastructure.outputport.IUserServicePort;
import com.socialred2025.publications.infrastructure.utils.ImageUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicationUseCase implements IPublicationInputPort {

    private final IPublicationMapper iPublicationMapper;

    private final IPublicationRepository iPublicationRepository;

    private final IImageRepository iImageRepository;

    private final ImageUtils imageUtils;

    private final IUserServicePort iUserServicePort;

    public PublicationUseCase(IPublicationMapper iPublicationMapper, ImageUtils imageUtils, IPublicationRepository iPublicationRepository, IImageRepository iImageRepository, IUserServicePort iUserServicePort) {
        this.imageUtils = imageUtils;
        this.iPublicationRepository = iPublicationRepository;
        this.iPublicationMapper = iPublicationMapper;
        this.iImageRepository = iImageRepository;
        this.iUserServicePort = iUserServicePort;
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
    public PublicationResponseDTO findPublication(Long id) throws PublicationNotFoundException {
        Publication publication = iPublicationRepository.findPublicationById(id).orElseThrow(() -> new PublicationNotFoundException("Publication not found with id: " + id));

        return iPublicationMapper.publicationToPublicationResponseDto(publication);
    }

    @Override
    public List<PublicationResponseDTO> feed(Long userId, String status, int page, int size) {
        List<PublicationResponseDTO> publicationResponseDTO = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, size);

        List<FriendDTO> friendDTOList = iUserServicePort.getAllFriends(userId, status);
        List<Long> friendIdList = friendDTOList.stream()
                .map(FriendDTO::getUserFriendId)
                .collect(Collectors.toList());

        List<Publication> publications = iPublicationRepository.findAllPublications(friendIdList, pageable);

        for (Publication p : publications){
            publicationResponseDTO.add(PublicationResponseDTO.builder()
                            .id(p.getId())
                            .userId(p.getUserId())
                            .description(p.getDescription())
                            .likesCount(p.getLikesCount())
                            .edited(p.isEdited())
                            .createdAt(p.getCreatedAt())
                            .updatedAt(p.getUpdatedAt())
                            .image(p.getImage())
                            .likes(p.getLikes())
                            .comments(p.getComments())
                    .build());
        }

        return publicationResponseDTO;
    }

    @Override
    public String deletePublication(Long id) throws PublicationNotFoundException {

        if (!iPublicationRepository.existsPublicationById(id)) {
            throw new PublicationNotFoundException("Publication not found with id: " + id);
        }

        iPublicationRepository.deletePublicationById(id);

        return "Post with id: " + id + " successfully deleted.";
    }
}
