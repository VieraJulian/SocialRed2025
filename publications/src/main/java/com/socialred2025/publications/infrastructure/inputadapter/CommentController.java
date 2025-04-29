package com.socialred2025.publications.infrastructure.inputadapter;

import com.socialred2025.publications.application.dto.*;
import com.socialred2025.publications.infrastructure.inputport.ICommentInputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/comments")
public class CommentController {

    private final ICommentInputPort commentInputPort;

    public CommentController(ICommentInputPort commentInputPort) {
        this.commentInputPort = commentInputPort;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiPublicationResponseDTO<?>> addComment(@RequestHeader("userId") Long userId, @RequestBody CommentRequestDTO commentRequestDTO) {
        try {
            CommentDTO comment = commentInputPort.addComment(userId, commentRequestDTO);

            ApiPublicationResponseDTO<CommentDTO> response = ApiPublicationResponseDTO.<CommentDTO>builder()
                    .success(true)
                    .data(comment)
                    .error(null)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating comment: {}", e.getMessage());

            ApiPublicationResponseDTO<String> response = ApiPublicationResponseDTO.<String>builder()
                    .success(false)
                    .data(null)
                    .error(e.getMessage())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
