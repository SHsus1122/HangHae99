package com.sparta.boardhomework.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;
//    public GlobalMsg(String msg, int statusCode) {
//        this.msg = msg;
//        this.statusCode = statusCode;
//    }
}
