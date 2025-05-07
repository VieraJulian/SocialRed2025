package com.socialred2025.users.application;

import com.socialred2025.users.application.dto.FollowDTO;
import com.socialred2025.users.application.dto.FollowRequestDTO;
import com.socialred2025.users.application.exception.AlreadyFollowingException;
import com.socialred2025.users.application.exception.SelfFollowException;
import com.socialred2025.users.application.exception.UserNotFoundException;
import com.socialred2025.users.application.mapper.IFollowMapper;
import com.socialred2025.users.domain.Follow;
import com.socialred2025.users.domain.UserEntity;
import com.socialred2025.users.infrastructure.inputport.IFollowInputPort;
import com.socialred2025.users.infrastructure.outputport.IFollowRepository;
import com.socialred2025.users.infrastructure.outputport.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowUseCase implements IFollowInputPort {

    private final IFollowRepository followRepository;

    private final IUserRepository userRepository;

    private final IFollowMapper followMapper;

    public FollowUseCase(IFollowRepository followRepository, IUserRepository userRepository, IFollowMapper followMapper) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
        this.followMapper = followMapper;
    }

    @Override
    public FollowDTO createFollow(Long userId, FollowRequestDTO followRequestDTO) throws UserNotFoundException, AlreadyFollowingException, SelfFollowException {
        Long followedId = followRequestDTO.getFollowedId();

        boolean existsFollow = followRepository.findByFollowerIdAndFollowedId(userId, followedId).isPresent();

        if (existsFollow) {
            throw new AlreadyFollowingException("The user is already following this profile.");
        }

        if (userId.equals(followedId)) {
            throw new SelfFollowException("You cannot follow yourself.");
        }

        UserEntity follower = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + followedId));

        UserEntity followed = userRepository.findUserById(followedId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + followedId));

        Follow followInfo = Follow.builder()
                .follower(follower)
                .followed(followed)
                .build();

        return followMapper.followToFollowDto(followRepository.saveFollow(followInfo));
    }

    @Override
    public String deleteFollow(Long userId, FollowRequestDTO followRequestDTO) {
        return "";
    }

    @Override
    public List<FollowDTO> findFollowersByFollowerId(Long userId) {
        return List.of();
    }
}
