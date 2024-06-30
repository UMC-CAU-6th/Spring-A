package com.umc.domain.board.service;

import com.umc.common.response.ApiResponse;
import com.umc.domain.board.dto.BoardRequestDto;
import com.umc.domain.board.dto.BoardResponseDto;
import com.umc.domain.board.entity.Board;
import com.umc.domain.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public void createBoard(BoardRequestDto request) {
        Board board = new Board();
        board.setName(request.getName());
        board.setDescription(request.getDescription());
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public ApiResponse<List<BoardResponseDto>> getAllBoards() {
        List<BoardResponseDto> boardList = boardRepository.findAll().stream()
                .map(board -> BoardResponseDto.builder()
                        .id(board.getId())
                        .name(board.getName())
                        .description(board.getDescription())
                        .build())
                .collect(Collectors.toList());

        return ApiResponse.onSuccess(boardList);
    }

    @Transactional(readOnly = true)
    public ApiResponse<BoardResponseDto> getBoardById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));

        BoardResponseDto response = BoardResponseDto.builder()
                .id(board.getId())
                .name(board.getName())
                .description(board.getDescription())
                .build();

        return ApiResponse.onSuccess(response);
    }

    @Transactional
    public ApiResponse<Void> updateBoard(Long id, BoardRequestDto request) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));

        board.setName(request.getName());
        board.setDescription(request.getDescription());
        boardRepository.save(board);

        return ApiResponse.onSuccess(null);
    }

    @Transactional
    public ApiResponse<Void> deleteBoard(Long id) {
        boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));

        boardRepository.deleteById(id);

        return ApiResponse.onSuccess(null);
    }
}
