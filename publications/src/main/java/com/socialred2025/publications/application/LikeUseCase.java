package com.socialred2025.publications.application;

import com.socialred2025.publications.application.dto.LikeDTO;
import com.socialred2025.publications.application.dto.LikeRequestDTO;
import com.socialred2025.publications.application.exception.LikeAlreadyExistsException;
import com.socialred2025.publications.application.mapper.ILikeMapper;
import com.socialred2025.publications.domain.Like;
import com.socialred2025.publications.infrastructure.inputport.ILikeInputPort;
import com.socialred2025.publications.infrastructure.outputport.ILikeRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeUseCase implements ILikeInputPort {

    private final ILikeRepository likeRepository;

    private final ILikeMapper likeMapper;

    public LikeUseCase(ILikeRepository likeRepository, ILikeMapper likeMapper) {
        this.likeRepository = likeRepository;
        this.likeMapper = likeMapper;
    }

    @Override
    public LikeDTO addLike(Long userId, LikeRequestDTO likeRequestDTO) throws LikeAlreadyExistsException {

        if (likeRepository.findLikeByUserIdAndPublicationId(userId, likeRequestDTO.getPublicationId())) {
            throw new LikeAlreadyExistsException("The user has already liked this post.");
        }

        Like like = Like.builder()
                .userId(userId)
                .publicationId(likeRequestDTO.getPublicationId())
                .build();

        return likeMapper.likeToLikeDto(likeRepository.saveLike(like));
    }

    @Override
    public String removeLike(Long userId, LikeRequestDTO likeRequestDTO) {
        return "";
    }
}
