package com.sparta.boardhomework.dto;

import com.sparta.boardhomework.entity.Board;
import lombok.Getter;

@Getter
public class BoardRequestDto {
    // 클라이언트 에서 넘어오는 이 객체를 통해서 받는다.
    private String boardName;
    private String username;
    private String password;
    private String contents;


}
