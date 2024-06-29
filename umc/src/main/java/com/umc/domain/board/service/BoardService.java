package com.umc.domain.board.service;

import com.umc.common.exception.handler.BoardHandler;
import com.umc.common.response.ApiResponse;
import com.umc.common.response.status.ErrorCode;
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
            throw new BoardHandler(ErrorCode.BOARD_ALREADY_EXIST);
        }

        Board board = Board.builder()
                .title(boardCreateRequestDTO.getTitle())
                .description(boardCreateRequestDTO.getDescription())
                .status(BoardStatus.USE)
                .build();
        BoardResponseDTO boardResponseDTO = new BoardResponseDTO(boardRepository.save(board));

        return ApiResponse.onSuccess(boardResponseDTO);
    }

    public ApiResponse<String> deleteBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardHandler(ErrorCode.BOARD_NOT_EXIST));
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
        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardHandler(ErrorCode.BOARD_NOT_EXIST));
        BoardResponseDTO boardResponseDTO = new BoardResponseDTO(board);

        return ApiResponse.onSuccess(boardResponseDTO);
    }
}
