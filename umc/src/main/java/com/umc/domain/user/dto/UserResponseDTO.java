package com.umc.domain.user.dto;


import com.umc.common.jwt.TokenInfo;
import lombok.*;

@Builder
@Getter
public class UserResponseDTO {
    TokenInfo tokenInfo;
    String nickName;

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PageResponse {
        String ResponseMessage;
    }
}
