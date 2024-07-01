package com.umc.domain.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardUpdateRequestDTO {
    private String title;

    @Builder
    public BoardUpdateRequestDTO(String title) {
        this.title = title;
    }
}
