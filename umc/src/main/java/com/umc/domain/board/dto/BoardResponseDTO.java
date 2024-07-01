package com.umc.domain.board.dto;

import com.umc.domain.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDTO {
    Long boardId;
    String title;
    Integer postNum;
    LocalDateTime created_at;
    LocalDateTime updated_at;
    public BoardResponseDTO(Board entity)
    {
        this.boardId=entity.getId();
        this.title=entity.getTitle();
        this.postNum=entity.getPostNum();
        this.created_at=entity.getCreatedAt();
        this.updated_at=entity.getUpdatedAt();
    }


}
