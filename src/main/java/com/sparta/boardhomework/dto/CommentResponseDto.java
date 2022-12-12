package com.sparta.boardhomework.dto;

import com.sparta.boardhomework.entity.Comment;
import com.sparta.boardhomework.entity.CommentLike;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long cmtId;
    private String username;
    private String cmtCnt;
    private Long likeCount;

    public CommentResponseDto(Comment comment) {
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.username = comment.getUsername();
        this.cmtId = comment.getCmtId();
        this.cmtCnt = comment.getCmtCnt();
        this.likeCount = (long) comment.getCommentLikes().size();
    }

}
