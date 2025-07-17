package com.socialred2025.users.infrastructure.inputadapter;

import com.socialred2025.users.application.dto.ApiUserResponseDTO;
import com.socialred2025.users.application.dto.FriendDTO;
import com.socialred2025.users.application.dto.FriendRequestDTO;
import com.socialred2025.users.application.dto.FriendStatusRequestDTO;
import com.socialred2025.users.infrastructure.inputport.IFriendInputPort;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
@Slf4j
public class FriendController {
    private final IFriendInputPort friendInputPort;

    public FriendController(IFriendInputPort friendInputPort){
        this.friendInputPort = friendInputPort;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiUserResponseDTO<?>> createFriend(@RequestHeader("userId") Long userId, @Valid @RequestBody FriendRequestDTO friendRequestDTO) {
        try {
            FriendDTO friendDTO = friendInputPort.createFriend(userId, friendRequestDTO);

            ApiUserResponseDTO<FriendDTO> response = ApiUserResponseDTO.<FriendDTO>builder()
                    .success(true)
                    .data(friendDTO)
                    .error(null)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating friend: {}", e.getMessage());

            ApiUserResponseDTO<String> response = ApiUserResponseDTO.<String>builder()
                    .success(false)
                    .data(null)
                    .error(e.getMessage())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<ApiUserResponseDTO<?>> statusFriend(@RequestHeader("userId") Long userId, @RequestParam String status) {
        try {
            List<FriendDTO> friendDTOList = friendInputPort.findFriendsByUserIdAndStatus(userId, status);

            ApiUserResponseDTO<List<FriendDTO>> response = ApiUserResponseDTO.<List<FriendDTO>>builder()
                    .success(true)
                    .data(friendDTOList)
                    .error(null)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Error getting friend: {}", e.getMessage());

            ApiUserResponseDTO<String> response = ApiUserResponseDTO.<String>builder()
                    .success(false)
                    .data(null)
                    .error(e.getMessage())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiUserResponseDTO<?>> getAllFriends(@RequestHeader("userId") Long userId) {
        try {
            List<FriendDTO> friendDTOList = friendInputPort.findFriendsByUserId(userId);

            ApiUserResponseDTO<List<FriendDTO>> response = ApiUserResponseDTO.<List<FriendDTO>>builder()
                    .success(true)
                    .data(friendDTOList)
                    .error(null)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Error getting friends: {}", e.getMessage());

            ApiUserResponseDTO<String> response = ApiUserResponseDTO.<String>builder()
                    .success(false)
                    .data(null)
                    .error(e.getMessage())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/status")
    public ResponseEntity<ApiUserResponseDTO<?>> updateFriendStatus(@RequestHeader("userId") Long userId, @RequestBody FriendStatusRequestDTO friendStatusRequestDTO) {
        try {
            FriendDTO friendDTO = friendInputPort.updateFriendStatus(userId, friendStatusRequestDTO);

            ApiUserResponseDTO<FriendDTO> response = ApiUserResponseDTO.<FriendDTO>builder()
                    .success(true)
                    .data(friendDTO)
                    .error(null)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating friendship: {}", e.getMessage());

            ApiUserResponseDTO<String> response = ApiUserResponseDTO.<String>builder()
                    .success(false)
                    .data(null)
                    .error(e.getMessage())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiUserResponseDTO<?>> deleteFriend(@RequestHeader("userId") Long userId, @RequestBody FriendRequestDTO friendRequestDTO){
        try {
            String msg = friendInputPort.deleteFriend(userId, friendRequestDTO);

            ApiUserResponseDTO<String> response = ApiUserResponseDTO.<String>builder()
                    .success(true)
                    .data(msg)
                    .error(null)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting friendship: {}", e.getMessage());

            ApiUserResponseDTO<String> response = ApiUserResponseDTO.<String>builder()
                    .success(false)
                    .data(null)
                    .error(e.getMessage())
                    .build();

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
