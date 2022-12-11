package com.sparta.boardhomework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.boardhomework.dto.CommentRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CMT_ID")
    private Long cmtId;

    private String cmtCnt;

    private String username;

    // 유저 ID 가져오는 관계설정
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    // 게시글 ID 가져오는 관계설정
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    public Comment(CommentRequestDto requestDto, Board board, User user) {
        this.cmtCnt = requestDto.getCmtCnt();
        this.board = board;
        this.user = user;
        this.username = user.getUsername();
    }

}
