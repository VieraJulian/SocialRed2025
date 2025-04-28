package com.socialred2025.publications.infrastructure.inputport;

import com.socialred2025.publications.application.dto.LikeDTO;
import com.socialred2025.publications.application.dto.LikeRequestDTO;
import com.socialred2025.publications.application.exception.LikeAlreadyExistsException;
import com.socialred2025.publications.application.exception.LikeNotFoundException;

public interface ILikeInputPort {

    LikeDTO addLike(Long userId, LikeRequestDTO likeRequestDTO) throws LikeAlreadyExistsException;
    String removeLike(Long userId, LikeRequestDTO likeRequestDTO);
}
