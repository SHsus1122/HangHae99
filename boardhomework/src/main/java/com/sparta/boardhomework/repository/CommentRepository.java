package com.sparta.boardhomework.repository;

import com.sparta.boardhomework.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBoardBoardId(Long id);

    Optional<Comment> findByUserUserId(Long id);
}
