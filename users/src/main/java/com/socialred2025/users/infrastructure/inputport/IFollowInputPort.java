package com.socialred2025.users.infrastructure.inputport;

import com.socialred2025.users.application.dto.FollowDTO;
import com.socialred2025.users.application.dto.FollowRequestDTO;
import com.socialred2025.users.application.exception.AlreadyFollowingException;
import com.socialred2025.users.application.exception.NotFollowingException;
import com.socialred2025.users.application.exception.SelfFollowException;
import com.socialred2025.users.application.exception.UserNotFoundException;

import java.util.List;

public interface IFollowInputPort {

    FollowDTO createFollow(Long userId, FollowRequestDTO followRequestDTO) throws UserNotFoundException, AlreadyFollowingException, SelfFollowException;
    String deleteFollow(Long userId, FollowRequestDTO followRequestDTO) throws NotFollowingException;
    List<FollowDTO> findFollowersByFollowerId(Long userId);
}
