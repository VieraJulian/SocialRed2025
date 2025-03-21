package com.socialred2025.publications.infrastructure.inputport;

import com.socialred2025.publications.application.dto.PublicationRequestDTO;
import com.socialred2025.publications.application.dto.PublicationResponseDTO;
import com.socialred2025.publications.application.dto.PublicationUpdateRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPublicationInputPort {

    PublicationResponseDTO createPublication(PublicationRequestDTO publicationRequestDTO, MultipartFile file);
    PublicationResponseDTO updatePublication(Long id, PublicationUpdateRequestDTO publicationUpdateRequestDTO, MultipartFile file);
    PublicationResponseDTO findPublication(Long id);
    List<PublicationResponseDTO> feed(Long userId, int page, int size);
    String deletePublication(Long id);
}
