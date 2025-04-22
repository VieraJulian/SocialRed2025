package com.socialred2025.publications.infrastructure.inputport;

import com.socialred2025.publications.application.dto.PublicationRequestDTO;
import com.socialred2025.publications.application.dto.PublicationResponseDTO;
import com.socialred2025.publications.application.dto.PublicationUpdateRequestDTO;
import com.socialred2025.publications.application.exception.ImageNotFoundException;
import com.socialred2025.publications.application.exception.UserNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IPublicationInputPort {

    PublicationResponseDTO createPublication(Long userId, PublicationRequestDTO publicationRequestDTO, MultipartFile file) throws UserNotFoundException, ImageNotFoundException, IOException;
    PublicationResponseDTO updatePublication(Long id, PublicationUpdateRequestDTO publicationUpdateRequestDTO, MultipartFile file);
    PublicationResponseDTO findPublication(Long id);
    List<PublicationResponseDTO> feed(Long userId, int page, int size);
    String deletePublication(Long id);
}
