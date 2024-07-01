package com.umc.domain.board.controller;

import com.umc.common.response.ApiResponse;
import com.umc.domain.board.dto.BoardRequestDto;
import com.umc.domain.board.dto.BoardResponseDto;
import com.umc.domain.board.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createBoard(@RequestBody BoardRequestDto request) {
        boardService.createBoard(request);
        return ResponseEntity.ok(ApiResponse.onSuccess(null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BoardResponseDto>>> getAllBoards(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        ApiResponse<List<BoardResponseDto>> response = boardService.getAllBoards(page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BoardResponseDto>> getBoardById(@PathVariable Long id) {
        ApiResponse<BoardResponseDto> response = boardService.getBoardById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto request) {
        ApiResponse<Void> response = boardService.updateBoard(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBoard(@PathVariable Long id) {
        ApiResponse<Void> response = boardService.deleteBoard(id);
        return ResponseEntity.ok(response);
    }
}
