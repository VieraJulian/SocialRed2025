package com.socialred2025.publications.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FriendStatusRequestDTO {

    private Long userFriendId;
    private String status;
}
