package com.umc.domain.post.Service;

import com.umc.common.exception.handler.BoardHandler;
import com.umc.common.exception.handler.UserHandler;
import com.umc.common.response.ApiResponse;
import com.umc.common.response.status.ErrorCode;
import com.umc.common.response.status.SuccessCode;
import com.umc.domain.board.entity.Board;
import com.umc.domain.board.repository.BoardRepository;
import com.umc.domain.post.dto.PostCreateRequestDTO;
import com.umc.domain.post.dto.PostListResponseDTO;
import com.umc.domain.post.dto.PostResponseDTO;
import com.umc.domain.post.dto.PostUpdateRequestDTO;
import com.umc.domain.post.entity.Post;
import com.umc.domain.post.repository.PostRepository;
import com.umc.domain.user.entity.Member;
import com.umc.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public ApiResponse<PostResponseDTO> createPost(PostCreateRequestDTO postCreateRequestDTO) {
        Board board = boardRepository.findById(postCreateRequestDTO.getBoardId()).orElseThrow(() -> new BoardHandler(ErrorCode.BOARD_NOT_EXIST));
        Member member = memberRepository.findById(postCreateRequestDTO.getPosterId()).orElseThrow(() -> new UserHandler(ErrorCode.MEMBER_NOT_FOUND));

        Post post = Post.builder()
                .title(postCreateRequestDTO.getTitle())
                .content(postCreateRequestDTO.getContent())
                .board(board)
                .poster(member)
                .build();

        PostResponseDTO postResponseDTO = new PostResponseDTO(postRepository.save(post));

        return ApiResponse.onSuccess(postResponseDTO);
    }

    public ApiResponse<String> deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(); // 오류 처리 필요
        postRepository.delete(post);

        return ApiResponse.of(SuccessCode._OK, "게시물이 성공적으로 삭제되었습니다.");
    }

    public ApiResponse<PostListResponseDTO> searchPosts(String title, Long posterId, String status) {
        if (title == null && posterId == null && status == null) {
            PostListResponseDTO postListResponseDTO = new PostListResponseDTO(postRepository.findAll());
            return ApiResponse.onSuccess(postListResponseDTO);
        } else if (title == null && posterId == null && status != null) {
            PostListResponseDTO postListResponseDTO = new PostListResponseDTO(postRepository.findPostsByStatus(status));
            return ApiResponse.onSuccess(postListResponseDTO);
        } else if (title == null && posterId != null && status == null) {
            Member poster = memberRepository.findById(posterId).orElseThrow(() -> new UserHandler(ErrorCode.MEMBER_NOT_FOUND));
            PostListResponseDTO postListResponseDTO = new PostListResponseDTO(postRepository.findPostsByPoster(poster));
            return ApiResponse.onSuccess(postListResponseDTO);
        } else if (title == null && posterId != null && status != null) {
            Member poster = memberRepository.findById(posterId).orElseThrow(() -> new UserHandler(ErrorCode.MEMBER_NOT_FOUND));
            PostListResponseDTO postListResponseDTO = new PostListResponseDTO(postRepository.findPostsByPosterAndStatus(poster, status));
            return ApiResponse.onSuccess(postListResponseDTO);
        } else if (title != null && posterId == null && status == null) {
            PostListResponseDTO postListResponseDTO = new PostListResponseDTO(postRepository.findPostsByTitle(title));
            return ApiResponse.onSuccess(postListResponseDTO);
        } else if (title != null && posterId == null && status != null) {
            PostListResponseDTO postListResponseDTO = new PostListResponseDTO(postRepository.findPostsByTitleAndStatus(title, status));
            return ApiResponse.onSuccess(postListResponseDTO);
        } else if (title != null && posterId != null && status == null) {
            Member poster = memberRepository.findById(posterId).orElseThrow(() -> new UserHandler(ErrorCode.MEMBER_NOT_FOUND));
            PostListResponseDTO postListResponseDTO = new PostListResponseDTO(postRepository.findPostsByTitleAndPoster(title, poster));
            return ApiResponse.onSuccess(postListResponseDTO);
        } else { // (title != null && posterId != null && status != null)
            Member poster = memberRepository.findById(posterId).orElseThrow(() -> new UserHandler(ErrorCode.MEMBER_NOT_FOUND));
            PostListResponseDTO postListResponseDTO = new PostListResponseDTO(postRepository.findPostsByTitleAndPosterAndStatus(title, poster, status));
            return ApiResponse.onSuccess(postListResponseDTO);
        }
    }

    public ApiResponse<PostListResponseDTO> searchPostsInBoard(Long boardId, String title, Long posterId, String status) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new BoardHandler(ErrorCode.MEMBER_NOT_FOUND));
        if (title == null && posterId == null && status == null) {
            PostListResponseDTO postListResponseDTO = new PostListResponseDTO(postRepository.findPostsByBoard(board));
            return ApiResponse.onSuccess(postListResponseDTO);
        } else if (title == null && posterId == null && status != null) {
            PostListResponseDTO postListResponseDTO = new PostListResponseDTO(postRepository.findPostsByBoardAndStatus(board, status));
            return ApiResponse.onSuccess(postListResponseDTO);
        } else if (title == null && posterId != null && status == null) {
            Member poster = memberRepository.findById(posterId).orElseThrow(() -> new UserHandler(ErrorCode.MEMBER_NOT_FOUND));
            PostListResponseDTO postListResponseDTO = new PostListResponseDTO(postRepository.findPostsByBoardAndPoster(board, poster));
            return ApiResponse.onSuccess(postListResponseDTO);
        } else if (title == null && posterId != null && status != null) {
            Member poster = memberRepository.findById(posterId).orElseThrow(() -> new UserHandler(ErrorCode.MEMBER_NOT_FOUND));
            PostListResponseDTO postListResponseDTO = new PostListResponseDTO(postRepository.findPostsByBoardAndPosterAndStatus(board, poster, status));
            return ApiResponse.onSuccess(postListResponseDTO);
        } else if (title != null && posterId == null && status == null) {
            PostListResponseDTO postListResponseDTO = new PostListResponseDTO(postRepository.findPostsByBoardAndTitle(board, title));
            return ApiResponse.onSuccess(postListResponseDTO);
        } else if (title != null && posterId == null && status != null) {
            PostListResponseDTO postListResponseDTO = new PostListResponseDTO(postRepository.findPostsByBoardAndTitleAndStatus(board, title, status));
            return ApiResponse.onSuccess(postListResponseDTO);
        } else if (title != null && posterId != null && status == null) {
            Member poster = memberRepository.findById(posterId).orElseThrow(() -> new UserHandler(ErrorCode.MEMBER_NOT_FOUND));
            PostListResponseDTO postListResponseDTO = new PostListResponseDTO(postRepository.findPostsByBoardAndTitleAndPoster(board, title, poster));
            return ApiResponse.onSuccess(postListResponseDTO);
        } else { // (title != null && posterId != null && status != null)
            Member poster = memberRepository.findById(posterId).orElseThrow(() -> new UserHandler(ErrorCode.MEMBER_NOT_FOUND));
            PostListResponseDTO postListResponseDTO = new PostListResponseDTO(postRepository.findPostsByBoardAndTitleAndPosterAndStatus(board, title, poster, status));
            return ApiResponse.onSuccess(postListResponseDTO);
        }
    }

    public ApiResponse<PostResponseDTO> updatePost(Long postId, PostUpdateRequestDTO postUpdateRequestDTO) {
        Post post = postRepository.findById(postId).orElseThrow(); // 예외 처리 필요
        if (postUpdateRequestDTO.getTitle() != "") {
            post.setTitle(postUpdateRequestDTO.getTitle());
        }
        if (postUpdateRequestDTO.getContent() != "") {
            post.setContent(postUpdateRequestDTO.getContent());
        }
        if (postUpdateRequestDTO.getStatus() != "") {
            post.setStatus(postUpdateRequestDTO.getStatus());
        }
        post.setBoard(boardRepository.findById(postUpdateRequestDTO.getBoardId()).orElseThrow(() -> new BoardHandler(ErrorCode.BOARD_NOT_EXIST)));

        PostResponseDTO postResponseDTO = new PostResponseDTO(postRepository.save(post));
        return ApiResponse.onSuccess(postResponseDTO);
    }
}
