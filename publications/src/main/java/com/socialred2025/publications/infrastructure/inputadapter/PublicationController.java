package com.socialred2025.publications.infrastructure.inputadapter;

import com.socialred2025.publications.application.dto.*;
import com.socialred2025.publications.infrastructure.inputport.IPublicationInputPort;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @GetMapping("/feed")
    public ResponseEntity<ApiPublicationResponseDTO<?>> feed(@RequestHeader("userId") Long userId,@RequestParam("status") String status, @RequestParam("page") int page, @RequestParam("size") int size){
        try {
            List<PublicationResponseDTO> publicationResponseDTO = iPublicationInputPort.feed(userId, status, page, size);

            ApiPublicationResponseDTO<List<PublicationResponseDTO>> response = ApiPublicationResponseDTO.<List<PublicationResponseDTO>>builder()
                    .success(true)
                    .data(publicationResponseDTO)
                    .error(null)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error getting feed: {}", e.getMessage());

            ApiPublicationResponseDTO<String> response = ApiPublicationResponseDTO.<String>builder()
                    .success(false)
                    .data(null)
                    .error(e.getMessage())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiPublicationResponseDTO<?>> deletePublication(@PathVariable Long id) {
        try {
            String msg = iPublicationInputPort.deletePublication(id);

            ApiPublicationResponseDTO<String> response = ApiPublicationResponseDTO.<String>builder()
                    .success(true)
                    .data(msg)
                    .error(null)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error deleting publication: {}", e.getMessage());

            ApiPublicationResponseDTO<String> response = ApiPublicationResponseDTO.<String>builder()
                    .success(false)
                    .data(null)
                    .error(e.getMessage())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
