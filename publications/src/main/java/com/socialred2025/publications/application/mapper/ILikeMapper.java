package com.socialred2025.publications.application.mapper;

import com.socialred2025.publications.application.dto.LikeDTO;
import com.socialred2025.publications.domain.Like;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ILikeMapper {

    LikeDTO likeToLikeDto(Like like);
}
