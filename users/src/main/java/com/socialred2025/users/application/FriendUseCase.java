package com.socialred2025.users.application;

import com.socialred2025.users.application.dto.FriendDTO;
import com.socialred2025.users.application.dto.FriendRequestDTO;
import com.socialred2025.users.application.dto.FriendStatusRequestDTO;
import com.socialred2025.users.application.exception.AlreadyFriendsException;
import com.socialred2025.users.application.exception.FriendshipNotFoundException;
import com.socialred2025.users.application.exception.SelfFriendException;
import com.socialred2025.users.application.exception.UserNotFoundException;
import com.socialred2025.users.application.mapper.IFriendMapper;
import com.socialred2025.users.domain.Friend;
import com.socialred2025.users.domain.StatusType;
import com.socialred2025.users.domain.UserEntity;
import com.socialred2025.users.infrastructure.inputport.IFriendInputPort;
import com.socialred2025.users.infrastructure.outputport.IFriendRepository;
import com.socialred2025.users.infrastructure.outputport.IUserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendUseCase implements IFriendInputPort {
    private final IFriendRepository friendRepository;
    private final IUserRepository userRepository;
    private final IFriendMapper friendMapper;

    public FriendUseCase(IFriendRepository friendRepository, IUserRepository userRepository, IFriendMapper friendMapper){
        this.friendRepository = friendRepository;
        this.userRepository = userRepository;
        this.friendMapper = friendMapper;
    }

    @Override
    public FriendDTO createFriend(Long userId, FriendRequestDTO friendRequestDTO) throws UserNotFoundException, SelfFriendException, AlreadyFriendsException {
        Long userFriendId = friendRequestDTO.getUserFriendId();

        UserEntity user = userRepository.findUserById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));

        UserEntity userFriend = userRepository.findUserById(userFriendId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userFriendId));

        if (userId.equals(userFriendId)) {
            throw new SelfFriendException("You cannot add yourself as a friend");
        }

        boolean existsFriendship = friendRepository.findByUserIdAndUserFriendId(userId, userFriendId).isPresent();

        if (existsFriendship) {
            throw new AlreadyFriendsException("You are already friends with this user");
        }

        Friend friendInfo = Friend.builder()
                .user(user)
                .userFriend(userFriend)
                .status(StatusType.PENDING)
                .build();

        return friendMapper.friendToFriendDto(friendRepository.saveFriend(friendInfo));

    }

    @Override
    public FriendDTO updateFriendStatus(Long userId, FriendStatusRequestDTO friendStatusRequestDTO) throws FriendshipNotFoundException {
        Long userFriendId = friendStatusRequestDTO.getUserFriendId();
        StatusType status = StatusType.valueOf(friendStatusRequestDTO.getStatus());

        Friend friendship = friendRepository.findByUserIdAndUserFriendId(userId, userFriendId)
                .orElseThrow(() -> new FriendshipNotFoundException("No friendship found between user " + userId + " and user " + userFriendId));

        if (friendship.getStatus() == status) {
            return friendMapper.friendToFriendDto(friendship);
        }

        friendship.setStatus(status);
        return friendMapper.friendToFriendDto(friendRepository.saveFriend(friendship));
    }

    @Override
    public List<FriendDTO> findFriendsByUserIdAndStatus(Long userId, String status) {
        List<Friend> friendList = friendRepository.findByUserIdAndStatus(userId, StatusType.valueOf(status));

        List<FriendDTO> friendListDTO = new ArrayList<>();

        for (Friend f : friendList) {
            friendListDTO.add(FriendDTO.builder()
                            .id(f.getId())
                            .userId(f.getUser().getId())
                            .userFriendId(f.getUserFriend().getId())
                            .status(f.getStatus().name())
                            .build());
        }

        return friendListDTO;
    }

    @Override
    public List<FriendDTO> findFriendsByUserId(Long userId) {
        return List.of();
    }

    @Override
    public String deleteFriend(Long userId, FriendRequestDTO friendRequestDTO) {
        return "";
    }
}
