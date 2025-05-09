package com.socialred2025.publications.application;

import com.socialred2025.publications.application.dto.LikeDTO;
import com.socialred2025.publications.application.dto.LikeRequestDTO;
import com.socialred2025.publications.application.exception.LikeAlreadyExistsException;
import com.socialred2025.publications.application.exception.LikeNotFoundException;
import com.socialred2025.publications.application.exception.PublicationNotFoundException;
import com.socialred2025.publications.application.exception.UnableToDeleteLikeException;
import com.socialred2025.publications.application.mapper.ILikeMapper;
import com.socialred2025.publications.domain.Like;
import com.socialred2025.publications.infrastructure.inputport.ILikeInputPort;
import com.socialred2025.publications.infrastructure.outputport.ILikeRepository;
import com.socialred2025.publications.infrastructure.outputport.IPublicationRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeUseCase implements ILikeInputPort {

    private final ILikeRepository likeRepository;

    private final ILikeMapper likeMapper;

    private final IPublicationRepository publicationRepository;

    public LikeUseCase(ILikeRepository likeRepository, ILikeMapper likeMapper, IPublicationRepository publicationRepository) {
        this.likeRepository = likeRepository;
        this.likeMapper = likeMapper;
        this.publicationRepository = publicationRepository;
    }

    @Override
    public LikeDTO addLike(Long userId, LikeRequestDTO likeRequestDTO) throws LikeAlreadyExistsException, PublicationNotFoundException {
        long publicationId = likeRequestDTO.getPublicationId();

        if (!publicationRepository.existsPublicationById(publicationId)) {
            throw new PublicationNotFoundException("Publication not found with id: " + publicationId);
        }

        if (likeRepository.findByUserIdAndPublicationId(userId, likeRequestDTO.getPublicationId()).isPresent()) {
            throw new LikeAlreadyExistsException("The user has already liked this post.");
        }

        Like like = Like.builder()
                .userId(userId)
                .publicationId(likeRequestDTO.getPublicationId())
                .build();

        return likeMapper.likeToLikeDto(likeRepository.saveLike(like));
    }

    @Override
    public String removeLike(Long userId, LikeRequestDTO likeRequestDTO) throws LikeNotFoundException, UnableToDeleteLikeException {

        Like like = likeRepository.findByUserIdAndPublicationId(userId, likeRequestDTO.getPublicationId())
                .orElseThrow(() -> new LikeNotFoundException("Like not found for this user and publication."));

        likeRepository.deleteLike(like.getId());

        if (likeRepository.existsLikeById(like.getId())) {
            throw new UnableToDeleteLikeException("The like could not be deleted successfully.");
        }

        return "Like successfully removed.";
    }

}
