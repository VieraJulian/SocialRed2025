package com.socialred2025.publications.infrastructure.inputport;

import com.socialred2025.publications.application.dto.CommentDTO;
import com.socialred2025.publications.application.dto.CommentRemoveRequestDTO;
import com.socialred2025.publications.application.dto.CommentRequestDTO;
import com.socialred2025.publications.application.exception.CommentLimitExceededException;
import com.socialred2025.publications.application.exception.PublicationNotFoundException;

public interface ICommentInputPort {

    CommentDTO addComment(Long userId, CommentRequestDTO commentRequestDTO) throws CommentLimitExceededException, PublicationNotFoundException;
    String removeComment(Long userId, CommentRemoveRequestDTO commentRemoveRequestDTO);
}
