package com.sparta.boardhomework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.boardhomework.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// @NoArgsConstructor 파라미터가 없는 기본 생성자를 생성해준다.
// Board board = new Board(); 이러한 것을 말한다...
@Getter
@Entity
@NoArgsConstructor
public class Board extends TimeStamped {
    // 게시글 index 값
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")
    private Long boardId;

    // 게시글 제목, 작성자명, 내용 은 null 일 수 없다.
    @Column(nullable = false)
    private String boardName;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    // @JsonIgnore 필드 레벨에서 무시 될 수 있는 속성
    // 데이터를 주고 받을 때 해당 데이터는 결과창에서 응답값에 보이지 않는다.
    @JsonIgnore
    @Column(nullable = false)
    private String password;

//    @Column(nullable = false)
//    private Long userId;

    // 게시글 참조하는 User 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    // 댓글을 다 가져오기 위해서
    // 댓글이 게시글을 참조하는 관계 설정
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    // 게시글 작성시 입력 요소
    public Board(BoardRequestDto requestDto, String username, String password, User user) {
        this.boardName = requestDto.getBoardName();
        this.contents = requestDto.getContents();
        this.username = username;
        this.password = password;
        this.user = user;
    }

    // 게시글 수정시 입력 요소
    public void update(BoardRequestDto requestDto, String username, String password) {
        this.boardName = requestDto.getBoardName();
        this.contents = requestDto.getContents();
        this.username = username;
        this.password = password;
    }
}

