package com.socialred2025.users.infrastructure.inputAdapter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialred2025.users.application.dto.UserCreateRequestDto;
import com.socialred2025.users.application.dto.UserResponseDTO;
import com.socialred2025.users.infrastructure.inputPort.IUserInputPort;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private IUserInputPort iUserInputPort;

    public UserController(IUserInputPort iUserInputPort) { 
        this.iUserInputPort = iUserInputPort;
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserCreateRequestDto createRequestDto) {
        try {
            UserResponseDTO userResponseDTO = iUserInputPort.createUser(createRequestDto);
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }  
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserDetail(@PathVariable Long id) {
        try {
            UserResponseDTO userResponseDTO = iUserInputPort.findUserById(id);
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error getting user: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }  
    }
    
    
}
