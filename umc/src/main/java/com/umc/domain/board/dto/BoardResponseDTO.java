package com.umc.domain.board.dto;

import com.umc.domain.board.entity.Board;
import com.umc.domain.board.entity.BoardStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BoardResponseDTO {

    private Long id;
    private String title;
    private String description;
    private BoardStatus status;
    //private List<PostResponseDTO> posts;

    public BoardResponseDTO(Board board) {
        id = board.getId();
        title = board.getTitle();
        description = board.getDescription();
        status = board.getStatus();
    }
}
