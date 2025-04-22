package com.socialred2025.publications.application.mapper;

import com.socialred2025.publications.application.dto.PublicationRequestDTO;
import com.socialred2025.publications.application.dto.PublicationResponseDTO;
import com.socialred2025.publications.domain.Publication;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPublicationMapper {

    Publication publicationRequestDtoToPublication(PublicationRequestDTO publicationRequestDTO);

    PublicationResponseDTO publicationToPublicationResponseDto(Publication publication);
}
