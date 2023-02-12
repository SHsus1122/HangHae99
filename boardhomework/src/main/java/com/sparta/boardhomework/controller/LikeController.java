package com.sparta.boardhomework.controller;

import com.sparta.boardhomework.security.UserDetailsImpl;
import com.sparta.boardhomework.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/boards/like/{id}")
    public ResponseEntity<String> addBoardLike(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        likeService.addBoardLike(userDetails.getUser(), id);
        return null;
    }

    @PostMapping("/boards/{boardId}/comment/like/{commentId}")
    public ResponseEntity<String> addCommentLike(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                 @PathVariable Long boardId,
                                                 @PathVariable Long commentId) {
        likeService.addCommentLike(userDetails.getUser(), boardId, commentId);
        return null;
    }
}
