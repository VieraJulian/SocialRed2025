package com.socialred2025.publications.infrastructure.inputadapter;

import com.socialred2025.publications.application.dto.ApiPublicationResponseDTO;
import com.socialred2025.publications.application.dto.PublicationRequestDTO;
import com.socialred2025.publications.application.dto.PublicationResponseDTO;
import com.socialred2025.publications.application.dto.PublicationUpdateRequestDTO;
import com.socialred2025.publications.infrastructure.inputport.IPublicationInputPort;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/publications")
@Slf4j
public class PublicationController {

    private final IPublicationInputPort iPublicationInputPort;

    public PublicationController(IPublicationInputPort iPublicationInputPort) {
        this.iPublicationInputPort = iPublicationInputPort;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiPublicationResponseDTO<?>> createPublication(@RequestHeader("userId") Long userId, @Valid @ModelAttribute PublicationRequestDTO publicationRequestDTO, @RequestParam("file") MultipartFile file) {
        try {
            PublicationResponseDTO publicationResponse = iPublicationInputPort.createPublication(userId, publicationRequestDTO, file);

            ApiPublicationResponseDTO<PublicationResponseDTO> response = ApiPublicationResponseDTO.<PublicationResponseDTO>builder()
                    .success(true)
                    .data(publicationResponse)
                    .error(null)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating publication: {}", e.getMessage());

            ApiPublicationResponseDTO<String> response = ApiPublicationResponseDTO.<String>builder()
                    .success(false)
                    .data(null)
                    .error(e.getMessage())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiPublicationResponseDTO<?>> updatePublication(@RequestHeader("userId") Long userId, @PathVariable Long id, @Valid @ModelAttribute PublicationUpdateRequestDTO publicationUpdateRequestDTO, @RequestParam("file") MultipartFile file) {
        try {
            PublicationResponseDTO publicationResponse = iPublicationInputPort.updatePublication(userId, id, publicationUpdateRequestDTO, file);

            ApiPublicationResponseDTO<PublicationResponseDTO> response = ApiPublicationResponseDTO.<PublicationResponseDTO>builder()
                    .success(true)
                    .data(publicationResponse)
                    .error(null)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error editing publication: {}", e.getMessage());

            ApiPublicationResponseDTO<String> response = ApiPublicationResponseDTO.<String>builder()
                    .success(false)
                    .data(null)
                    .error(e.getMessage())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiPublicationResponseDTO<?>> findPublication(@PathVariable Long id) {
        try {
            PublicationResponseDTO publicationResponse = iPublicationInputPort.findPublication(id);

            ApiPublicationResponseDTO<PublicationResponseDTO> response = ApiPublicationResponseDTO.<PublicationResponseDTO>builder()
                    .success(true)
                    .data(publicationResponse)
                    .error(null)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error getting publication: {}", e.getMessage());

            ApiPublicationResponseDTO<String> response = ApiPublicationResponseDTO.<String>builder()
                    .success(false)
                    .data(null)
                    .error(e.getMessage())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
