package com.sparta.boardhomework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Builder
//@AllArgsConstructor
@NoArgsConstructor
public class MsgResponseDto {
    private String msg;
    private int statusCode;

    public MsgResponseDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
