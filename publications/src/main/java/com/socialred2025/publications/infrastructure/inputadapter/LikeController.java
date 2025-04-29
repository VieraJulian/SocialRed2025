package com.socialred2025.publications.infrastructure.inputadapter;

import com.socialred2025.publications.application.dto.ApiPublicationResponseDTO;
import com.socialred2025.publications.application.dto.LikeDTO;
import com.socialred2025.publications.application.dto.LikeRequestDTO;
import com.socialred2025.publications.infrastructure.inputport.ILikeInputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/likes")
public class LikeController {

    private final ILikeInputPort likeInputPort;

    public LikeController (ILikeInputPort likeInputPort) {
        this.likeInputPort = likeInputPort;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiPublicationResponseDTO<?>> addLike(@RequestHeader("userId") Long userId, @RequestBody LikeRequestDTO likeRequestDTO) {
        try {
            LikeDTO likeDTO = likeInputPort.addLike(userId, likeRequestDTO);

            ApiPublicationResponseDTO<LikeDTO> response = ApiPublicationResponseDTO.<LikeDTO>builder()
                    .success(true)
                    .data(likeDTO)
                    .error(null)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating like: {}", e.getMessage());

            ApiPublicationResponseDTO<String> response = ApiPublicationResponseDTO.<String>builder()
                    .success(false)
                    .data(null)
                    .error(e.getMessage())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<ApiPublicationResponseDTO<?>> removeLike(@RequestHeader("userId") Long userId, @RequestBody LikeRequestDTO likeRequestDTO) {
        try {
            String msj = likeInputPort.removeLike(userId, likeRequestDTO);

            ApiPublicationResponseDTO<String> response = ApiPublicationResponseDTO.<String>builder()
                    .success(true)
                    .data(msj)
                    .error(null)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error deleting like: {}", e.getMessage());

            ApiPublicationResponseDTO<String> response = ApiPublicationResponseDTO.<String>builder()
                    .success(false)
                    .data(null)
                    .error(e.getMessage())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
