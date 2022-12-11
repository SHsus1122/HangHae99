package com.sparta.boardhomework.repository;

import com.sparta.boardhomework.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    // List 에 모든 게시글을 담아서 가져온다.
    List<Board> findAllByOrderByModifiedAtDesc();

//    Optional<Board> findByIdAndUserId(Long id, Long userId);

    // 게시글 하나만 조회하는 기능
    Optional<Board> findByBoardId(Long id);

}
