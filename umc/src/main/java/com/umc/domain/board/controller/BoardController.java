package com.umc.domain.board.controller;

import com.umc.common.response.ApiResponse;
import com.umc.domain.board.dto.BoardRequestDTO;
import com.umc.domain.board.dto.BoardResponseDTO;
import com.umc.domain.board.service.BoardService;
import com.umc.domain.user.dto.MemberLoginRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor

public class BoardController {

    private final BoardService boardService;

    @CrossOrigin
    @Operation(summary = "게시판 생성 API")
    @PostMapping("/")
    public ApiResponse<BoardResponseDTO> createBoard(@Valid @RequestBody BoardRequestDTO request) {
        return boardService.createBoard(request);
    }

    @CrossOrigin
    @Operation(summary = "게시판 업데이트 API")
    @PutMapping("/{board_id")
    public ApiResponse<BoardResponseDTO> updateBoard(@PathVariable Long board_id, @Valid @RequestBody BoardRequestDTO boardRequestDTO) {
        return boardService.updateBoard(board_id, boardRequestDTO);
    }

    @CrossOrigin
    @Operation(summary = "게시판 삭제 API")
    @DeleteMapping("/{board_id")
    public ApiResponse<BoardResponseDTO> deleteBoard(@PathVariable Long board_id,@Valid @RequestBody BoardRequestDTO boardRequestDTO) {
        return boardService.deleteBoard(board_id, boardRequestDTO);
    }
    @CrossOrigin
    @Operation(summary = "게시판 전체 조회 API")
    @GetMapping("/{board_id")
    public List<BoardResponseDTO> getBoard(@Valid @RequestBody BoardRequestDTO boardRequestDTO) {
        return boardService.getBoard();
    }

    @CrossOrigin
    @Operation(summary = "게시판 조회 API")
    @GetMapping("/{board_id")
    public ApiResponse<BoardResponseDTO> findByIdBoard(@PathVariable Long board_id) {
        return boardService.findBoard(board_id);
    }
}

