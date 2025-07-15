package com.socialred2025.users.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FriendStatusRequestDTO {

    @NotNull
    private Long userFriendId;

    @NotNull
    private String status;
}
