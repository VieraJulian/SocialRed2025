package com.socialred2025.publications.application.dto;

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
