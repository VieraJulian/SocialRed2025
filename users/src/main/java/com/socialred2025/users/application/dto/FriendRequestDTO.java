package com.socialred2025.users.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FriendRequestDTO {

    @NotNull
    private Long userFriendId;
}
