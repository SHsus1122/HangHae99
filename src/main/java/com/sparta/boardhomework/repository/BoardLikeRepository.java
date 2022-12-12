package com.sparta.boardhomework.repository;

import com.sparta.boardhomework.entity.Board;
import com.sparta.boardhomework.entity.BoardLike;
import com.sparta.boardhomework.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {
    Optional<BoardLike> findByUserAndBoard(User user, Board board);
    // findByUserUserIdAndBoardBoardId

    void deleteByUserAndBoard(User user, Board board);

}
