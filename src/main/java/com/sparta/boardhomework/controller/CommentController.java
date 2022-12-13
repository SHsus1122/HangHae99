package com.sparta.boardhomework.controller;

import com.sparta.boardhomework.dto.*;
import com.sparta.boardhomework.security.UserDetailsImpl;
import com.sparta.boardhomework.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

//    @GetMapping("/{id}")
//    public CommentResponseDto commentResponseDto

    @PostMapping("/{id}")
    public CommentResponseDto createComment(@PathVariable Long id,
                                            @RequestBody CommentRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(id, requestDto, userDetails.getUser());
    }

    @PutMapping("/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id,
                                            @RequestBody CommentRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.updateComment(id, requestDto, userDetails.getUser());
    }

    @DeleteMapping("/{id}")
    public PassResponseDto deleteComment(@PathVariable Long id,
                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(id, userDetails.getUser());
        return new PassResponseDto(HttpStatus.OK.value(), "삭제 완료");
    }
}
