package com.socialred2025.publications.application.mapper;

import com.socialred2025.publications.application.dto.CommentDTO;
import com.socialred2025.publications.domain.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICommentMapper {

    CommentDTO commentToCommentDto(Comment comment);
}
