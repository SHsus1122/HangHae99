package com.sparta.boardhomework.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardRequestDto {
    // 클라이언트 에서 넘어오는 이 객체를 통해서 받는다.
    private String boardName;
    private String contents;
}
