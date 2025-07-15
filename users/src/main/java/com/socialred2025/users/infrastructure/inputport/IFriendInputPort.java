package com.socialred2025.users.infrastructure.inputport;

import com.socialred2025.users.application.dto.FriendDTO;
import com.socialred2025.users.application.dto.FriendRequestDTO;
import com.socialred2025.users.application.exception.AlreadyFriendsException;
import com.socialred2025.users.application.exception.SelfFriendException;
import com.socialred2025.users.application.exception.UserNotFoundException;

import java.util.List;

public interface IFriendInputPort {

    FriendDTO createFriend(Long userId, FriendRequestDTO friendRequestDTO) throws UserNotFoundException, SelfFriendException, AlreadyFriendsException;
    String deleteFriend(Long userId, FriendRequestDTO friendRequestDTO);
    List<FriendDTO> findFriendsByUserId(Long userId);
    List<FriendDTO> findFriendsByUserIdAndStatus(Long userId, String status);

}
