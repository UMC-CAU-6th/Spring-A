package com.umc.domain.board.service;

import com.umc.common.response.ApiResponse;
import com.umc.domain.board.dto.BoardRequestDTO;
import com.umc.domain.board.dto.BoardResponseDTO;
import com.umc.domain.board.entity.Board;
import com.umc.domain.board.repository.BoardRepository;
import com.umc.domain.user.entity.Member;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public ApiResponse<BoardResponseDTO> createBoard(BoardRequestDTO request){
        Board board=Board.builder()
                .title(request.getTitle())
                .postNum(request.getPostNum())
                .build();
        boardRepository.save(board);

        return ApiResponse.onSuccess(new BoardResponseDTO(board));
    }


    @Transactional
    public Long updateBoard(Long board_id, BoardRequestDTO request) {

        Board board = BoardRepository.findById(board_id).orElseThrow(() -> new IllegalArgumentException("해당 게시판이 없습니다. id=" + board_id));
        board.updateBoard(request.getTitle());
        boardRepository.save(board);
        return board_id;
    }

    @Transactional(readOnly = true)
    public List<BoardResponseDTO> getBoard() {
        return boardRepository.findAllByOrderByUpdatedAtDesc().stream().map(BoardResponseDTO::new).toList();
    }

    @Transactional
    public void deleteBoard(int board_id) {
        Board board = boardRepository.findById(board_id).orElseThrow(() -> {
            return new IllegalArgumentException("Board Id를 찾을 수 없습니다!");
        });
        // 게시글이 있는 경우 삭제처리
        boardRepository.deleteById(board_id);


    }
}
