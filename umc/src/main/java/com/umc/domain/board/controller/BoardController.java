package com.umc.domain.board.controller;

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
    public void createBoard(@RequestBody BoardRequestDto request) {
        boardService.createBoard(request);
    }

    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> getAllBoards() {
        List<BoardResponseDto> boards = boardService.getAllBoards();
        return ResponseEntity.ok(boards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> getBoardById(@PathVariable Long id) {
        BoardResponseDto board = boardService.getBoardById(id);
        return ResponseEntity.ok(board);
    }

    @PutMapping("/{id}")
    public void updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto request) {
        boardService.updateBoard(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
    }
}
