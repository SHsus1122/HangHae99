package com.sparta.boardhomework.repository;

import com.sparta.boardhomework.entity.Board;
import com.sparta.boardhomework.entity.BoardLike;
import com.sparta.boardhomework.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 부가기능 처리 : 횡단 관심사
public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {
    Optional<BoardLike> findByUserAndBoard(User user, Board board);
    // findByUserUserIdAndBoardBoardId

    void deleteByUserAndBoard(User user, Board board);

}
