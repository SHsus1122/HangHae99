package com.sparta.boardhomework.service;

import com.sparta.boardhomework.dto.BoardRequestDto;
import com.sparta.boardhomework.dto.CommentRequestDto;
import com.sparta.boardhomework.dto.CommentResponseDto;
import com.sparta.boardhomework.dto.MsgResponseDto;
import com.sparta.boardhomework.entity.Board;
import com.sparta.boardhomework.entity.Comment;
import com.sparta.boardhomework.entity.User;
import com.sparta.boardhomework.entity.UserRoleEnum;
import com.sparta.boardhomework.repository.BoardRepository;
import com.sparta.boardhomework.repository.CommentRepository;
import com.sparta.boardhomework.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final BoardRepository boardRepository;

    private final CommentRepository commentRepository;

    @Transactional
    public CommentResponseDto createComment(Long id, CommentRequestDto requestDto, User user) {

        if (user != null) {

            Board board = boardRepository.findByBoardId(id).orElseThrow(
                    () -> new RuntimeException("게시글이 존재하지 않습니다."));

            Comment comment = commentRepository.save(new Comment(requestDto, board, user));

            return new CommentResponseDto(comment);
        }
        return null;
    }

    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto, User user) {

        if (user != null) {

            Board board = boardRepository.findByBoardId(id).orElseThrow(
                    () -> new RuntimeException("게시글이 존재하지 않습니다."));

            if (user.getRole() == UserRoleEnum.ADMIN || board.getUsername().equals(user.getUsername())) {
                Comment comment = commentRepository.save(new Comment(requestDto, board, user));
                return new CommentResponseDto(comment);
            } else {
                throw new IllegalArgumentException("해당 댓글을 삭제할 권한이 없습니다.");
            }
        }
        return null;
    }

    @Transactional
    public MsgResponseDto deleteComment(Long id, User user) {

        if (user != null) {

            Comment comment = commentRepository.findById(id).orElseThrow(
                    () -> new NullPointerException("존재하지 않는 댓글입니다."));

            if (user.getRole() == UserRoleEnum.ADMIN || comment.getUsername().equals(user.getUsername())) {
                commentRepository.delete(comment);
                return new MsgResponseDto("삭제 성공", HttpStatus.OK.value());
            } else {
                throw new IllegalArgumentException("해당 댓글을 삭제할 권한이 없습니다.");
            }
        }

        return null;
    }
}
