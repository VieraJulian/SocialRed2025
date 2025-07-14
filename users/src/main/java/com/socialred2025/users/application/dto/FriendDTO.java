package com.socialred2025.users.application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendDTO {
    private Long id;
    private Long userId;
    private Long userFriendId;
    private String status;
}
