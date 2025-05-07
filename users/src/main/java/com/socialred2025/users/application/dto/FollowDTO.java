package com.socialred2025.users.application.dto;

import com.socialred2025.users.domain.UserEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FollowDTO {

    private Long id;
    private Long followerId;
    private Long followedId;
}
