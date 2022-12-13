package com.sparta.boardhomework.repository;

import com.sparta.boardhomework.entity.Comment;
import com.sparta.boardhomework.entity.CommentLike;
import com.sparta.boardhomework.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    Optional<CommentLike> findByUserAndComment(User user, Comment comment);

    void deleteByUserAndComment(User user, Comment comment);
}
