package com.sparta.boardhomework.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.boardhomework.dto.CommentRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CMT_ID")
    private Long cmtId;

    @Column(nullable = false)
    private String cmtCnt;

    private String username;

    private Long likeCount;

    // 유저 ID 가져오는 관계설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    // 게시글 ID 가져오는 관계설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @OneToMany(mappedBy = "comment")
    private List<CommentLike> commentLikes = new ArrayList<>();


    public Comment(CommentRequestDto requestDto, Board board, User user) {
        this.cmtCnt = requestDto.getCmtCnt();
        this.board = board;
        this.user = user;
        this.username = user.getUsername();
    }

}
