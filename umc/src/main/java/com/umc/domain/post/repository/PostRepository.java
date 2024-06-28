package com.umc.domain.post.repository;

import com.umc.domain.board.entity.Board;
import com.umc.domain.post.entity.Post;
import com.umc.domain.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findById(Long id);

    // 전체 게시물 대상
    List<Post> findPostsByStatus(String status);

    List<Post> findPostsByPoster(Member poster);

    List<Post> findPostsByPosterAndStatus(Member poster, String status);

    List<Post> findPostsByTitle(String title);

    List<Post> findPostsByTitleAndStatus(String title, String status);

    List<Post> findPostsByTitleAndPoster(String title, Member poster);

    List<Post> findPostsByTitleAndPosterAndStatus(String title, Member poster, String status);

    // 한 게시판 대상
    List<Post> findPostsByBoard(Board board);

    List<Post> findPostsByBoardAndStatus(Board board, String status);

    List<Post> findPostsByBoardAndPoster(Board board, Member poster);

    List<Post> findPostsByBoardAndPosterAndStatus(Board board, Member poster, String status);

    List<Post> findPostsByBoardAndTitle(Board board, String title);

    List<Post> findPostsByBoardAndTitleAndStatus(Board board, String title, String status);

    List<Post> findPostsByBoardAndTitleAndPoster(Board board, String title, Member poster);

    List<Post> findPostsByBoardAndTitleAndPosterAndStatus(Board board, String title, Member poster, String status);
}
