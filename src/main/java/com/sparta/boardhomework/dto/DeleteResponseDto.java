package com.sparta.boardhomework.dto;

import lombok.Getter;

@Getter
public class DeleteResponseDto {
    private String msg;

    private boolean result;

    public DeleteResponseDto(String msg, boolean result) {
        this.msg = msg;
        this.result = result;
    }
}
