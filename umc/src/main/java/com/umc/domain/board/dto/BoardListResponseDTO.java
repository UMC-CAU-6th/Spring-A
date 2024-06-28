package com.umc.domain.board.dto;

import com.umc.domain.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class BoardListResponseDTO {
    private Integer numberOfBoards;
    private List<ReducedBoard> boardList;

    @AllArgsConstructor
    private class ReducedBoard {
        private Long id;
        private String title;
    }

    public BoardListResponseDTO(List<Board> boardList) {
        this.boardList = boardList.stream()
                .map(board -> new ReducedBoard(board.getId(), board.getTitle()))
                .collect(Collectors.toList());
        this.numberOfBoards = boardList.size();
    }
}
