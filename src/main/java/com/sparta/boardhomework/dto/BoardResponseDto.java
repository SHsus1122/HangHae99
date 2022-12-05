package com.sparta.boardhomework.dto;

import com.sparta.boardhomework.entity.Board;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardResponseDto {
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long id;
    private String boardName;
    private String username;
    private String contents;


    public BoardResponseDto(Board board) {
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
        this.id = board.getId();
        this.boardName = board.getBoardName();
        this.username = board.getUsername();
        this.contents = board.getContents();
    }
}