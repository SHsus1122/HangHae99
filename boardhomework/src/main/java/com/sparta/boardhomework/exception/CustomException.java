package com.sparta.boardhomework.exception;

import lombok.*;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    // RuntimeException 을 상속하는 이유는 특정짓지 않았고
    // RuntimeException 은 개발자가 의도적으로 잡아주는 예외처리로 많이 쓰인다.
    private final ErrorCode errorCode;
//    public GlobalMsg(String msg, int statusCode) {
//        this.msg = msg;
//        this.statusCode = statusCode;
//    }
}
