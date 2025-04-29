package com.socialred2025.publications.application;

import com.socialred2025.publications.application.dto.CommentDTO;
import com.socialred2025.publications.application.dto.CommentRemoveRequestDTO;
import com.socialred2025.publications.application.dto.CommentRequestDTO;
import com.socialred2025.publications.application.exception.CommentLimitExceededException;
import com.socialred2025.publications.application.exception.PublicationNotFoundException;
import com.socialred2025.publications.application.mapper.ICommentMapper;
import com.socialred2025.publications.domain.Comment;
import com.socialred2025.publications.infrastructure.inputport.ICommentInputPort;
import com.socialred2025.publications.infrastructure.outputport.ICommentRepository;
import com.socialred2025.publications.infrastructure.outputport.IPublicationRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentUseCase implements ICommentInputPort {

    private final ICommentRepository commentRepository;

    private final IPublicationRepository publicationRepository;

    private final ICommentMapper commentMapper;

    public CommentUseCase(ICommentRepository commentRepository, ICommentMapper commentMapper, IPublicationRepository publicationRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.publicationRepository = publicationRepository;
    }

    @Override
    public CommentDTO addComment(Long userId, CommentRequestDTO commentRequestDTO) throws CommentLimitExceededException, PublicationNotFoundException {
        long publicationId = commentRequestDTO.getPublicationId();
        String commentDescription = commentRequestDTO.getCommentDescription();

        if (!publicationRepository.existsPublicationById(publicationId)) {
            throw new PublicationNotFoundException("Publication not found with id: " + publicationId);
        }

        long numberOfCommentsByUser = commentRepository.countByUserIdAndPublicationId(userId, publicationId);

        if (numberOfCommentsByUser >= 5) {
            throw new CommentLimitExceededException("The user has exceeded the limit of 5 comments for this publication.");
        }

        Comment comment = Comment.builder()
                .commentDescription(commentDescription)
                .userId(userId)
                .publicationId(publicationId)
                .build();

        return commentMapper.commentToCommentDto(commentRepository.saveComment(comment));
    }

    @Override
    public String removeComment(Long userId, CommentRemoveRequestDTO commentRemoveRequestDTO) {
        return "";
    }
}
