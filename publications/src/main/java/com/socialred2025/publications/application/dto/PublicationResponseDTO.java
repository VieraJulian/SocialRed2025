package com.socialred2025.publications.application.dto;

import com.socialred2025.publications.domain.Comment;
import com.socialred2025.publications.domain.Image;
import com.socialred2025.publications.domain.Like;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublicationResponseDTO {

    private Long id;
    private Long userId;
    private String description;
    private int likesCount;
    private boolean edited;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Image image;
    private Set<Like> likes;
    private Set<Comment> comments;
}
