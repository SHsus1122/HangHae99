package com.sparta.boardhomework.service;

import com.sparta.boardhomework.dto.CommentRequestDto;
import com.sparta.boardhomework.dto.CommentResponseDto;
import com.sparta.boardhomework.dto.PassResponseDto;
import com.sparta.boardhomework.exception.CustomException;
import com.sparta.boardhomework.entity.Board;
import com.sparta.boardhomework.entity.Comment;
import com.sparta.boardhomework.entity.User;
import com.sparta.boardhomework.entity.UserRoleEnum;
import com.sparta.boardhomework.exception.ErrorCode;
import com.sparta.boardhomework.repository.BoardRepository;
import com.sparta.boardhomework.repository.CommentRepository;
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

        Board board = boardRepository.findByBoardId(id).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_BOARD));

        Comment comment = commentRepository.save(new Comment(requestDto, board, user));

        return new CommentResponseDto(comment);

    }

    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto, User user) {

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_COMMENT));

        if (user.getRole() == UserRoleEnum.ADMIN || comment.getUsername().equals(user.getUsername())) {
            // 영속성 컨텍스트 더티 체킹
            // @Transactional 이게 없으면 업데이트가 안되는데 왜 안되는지 <- ***
            comment.update(requestDto);
            return new CommentResponseDto(comment);
        } else {
            throw new CustomException(ErrorCode.INVALID_AUTH_COMMENT);
        }

    }


    @Transactional
    public PassResponseDto deleteComment(Long id, User user) {

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_COMMENT));

        if (user.getRole() == UserRoleEnum.ADMIN || comment.getUsername().equals(user.getUsername())) {
            commentRepository.delete(comment);
            return new PassResponseDto(HttpStatus.OK.value(), "삭제 완료");
        } else {
            throw new CustomException(ErrorCode.INVALID_AUTH_COMMENT);
        }

    }
}
