package com.umc.domain.board.service;

import com.umc.common.response.ApiResponse;
import com.umc.common.response.status.SuccessCode;
import com.umc.domain.board.dto.BoardCreateRequestDTO;
import com.umc.domain.board.dto.BoardListResponseDTO;
import com.umc.domain.board.dto.BoardResponseDTO;
import com.umc.domain.board.entity.Board;
import com.umc.domain.board.entity.BoardStatus;
import com.umc.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public ApiResponse<BoardResponseDTO> createBoard(BoardCreateRequestDTO boardCreateRequestDTO) {
        if (!boardRepository.findByTitle(boardCreateRequestDTO.getTitle()).isEmpty()) {
            //게시판 이름 중복
        }

        Board board = Board.builder()
                .title(boardCreateRequestDTO.getTitle())
                .description(boardCreateRequestDTO.getDescription())
                .build();
        BoardResponseDTO boardResponseDTO = new BoardResponseDTO(boardRepository.save(board));

        return ApiResponse.onSuccess(boardResponseDTO);
    }

    public ApiResponse<String> deleteBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(); //예외처리 필요
        boardRepository.delete(board);

        return ApiResponse.of(SuccessCode._OK, "게시판이 성공적으로 삭제되었습니다.");
    }

    public ApiResponse<BoardListResponseDTO> getBoardList(String title, String statusString) {
        if (title == null && statusString == null) {
            BoardListResponseDTO boardListResponseDTO = new BoardListResponseDTO(boardRepository.findAll());
            return ApiResponse.onSuccess(boardListResponseDTO);
        }
        if (title != null) {
            BoardListResponseDTO boardListResponseDTO = new BoardListResponseDTO(boardRepository.findBoardsByTitle(title));
            return ApiResponse.onSuccess(boardListResponseDTO);
        } else {
            BoardStatus boardStatus = BoardStatus.fromString(statusString);
            BoardListResponseDTO boardListResponseDTO = new BoardListResponseDTO(boardRepository.findBoardsByStatus(boardStatus));
            return ApiResponse.onSuccess(boardListResponseDTO);
        }

    }

    public ApiResponse<BoardResponseDTO> getBoardById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(); // 예외처리 필요
        BoardResponseDTO boardResponseDTO = new BoardResponseDTO(board);

        return ApiResponse.onSuccess(boardResponseDTO);
    }
}
