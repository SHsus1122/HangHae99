package com.sparta.boardhomework.service;

import com.sparta.boardhomework.entity.*;
import com.sparta.boardhomework.exception.CustomException;
import com.sparta.boardhomework.exception.ErrorCode;
import com.sparta.boardhomework.repository.BoardLikeRepository;
import com.sparta.boardhomework.repository.BoardRepository;
import com.sparta.boardhomework.repository.CommentLikeRepository;
import com.sparta.boardhomework.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final BoardLikeRepository boardLikeRepository;
    private final BoardRepository boardRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void addBoardLike(User user, Long id) {

        Board board = boardRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_BOARD)
        );

        // 유저 ID , 게시글 ID , 좋아요 ID
        // isEmpty 값이 비었는지 확인하는 함수 (옵셔널)
        // existsBy 를 사용해서도 존재 확인이 가능하다.
        if (boardLikeRepository.findByUserAndBoard(user, board).isEmpty()) {
            // BoardLike 에 데이터를 넣어주고 board 에도 넣어줘야 한다.
            // 양방향에서는 즉 양쪽을 다 넣어줘야 한다.
            // 해야하는 이유 : 게시글을 조회할때 게시글이 가지고 좋아요 정보를 가져올 수 있다.
            // board.getBoardLike();
            BoardLike boardLike = boardLikeRepository.save(new BoardLike(user, board));
            board.getBoardLikes().add(boardLike);
        } else {
            boardLikeRepository.deleteByUserAndBoard(user, board);
        }

    }

    @Transactional
    public void addCommentLike(User user, Long boardId, Long commentId) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_COMMENT)
        );

        Board board = boardRepository.findByBoardId(boardId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_BOARD)
        );

        if (commentLikeRepository.findByUserAndComment(user, comment).isEmpty()) {
            CommentLike commentLike = commentLikeRepository.save(new CommentLike(user, comment));
            comment.getCommentLikes().add(commentLike);
        } else {
            commentLikeRepository.deleteByUserAndComment(user, comment);
        }

    }
}
