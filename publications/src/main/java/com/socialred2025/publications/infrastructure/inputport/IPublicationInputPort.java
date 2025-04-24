package com.socialred2025.publications.infrastructure.inputport;

import com.socialred2025.publications.application.dto.PublicationRequestDTO;
import com.socialred2025.publications.application.dto.PublicationResponseDTO;
import com.socialred2025.publications.application.dto.PublicationUpdateRequestDTO;
import com.socialred2025.publications.application.exception.ImageNotFoundException;
import com.socialred2025.publications.application.exception.PublicationNotFoundException;
import com.socialred2025.publications.application.exception.UnauthorizedActionException;
import com.socialred2025.publications.application.exception.UserNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IPublicationInputPort {

    PublicationResponseDTO createPublication(Long userId, PublicationRequestDTO publicationRequestDTO, MultipartFile file) throws UserNotFoundException, ImageNotFoundException, IOException;
    PublicationResponseDTO updatePublication(Long uiserId, Long id, PublicationUpdateRequestDTO publicationUpdateRequestDTO, MultipartFile file) throws PublicationNotFoundException, UnauthorizedActionException, IOException;
    PublicationResponseDTO findPublication(Long id) throws PublicationNotFoundException;
    List<PublicationResponseDTO> feed(Long userId, int page, int size);
    String deletePublication(Long id) throws PublicationNotFoundException;
}
