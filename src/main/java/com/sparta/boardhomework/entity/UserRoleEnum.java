package com.sparta.boardhomework.entity;

public enum  UserRoleEnum {

    // 이 부분의 의미가 무엇인지...
    USER(Authority.USER),
    ADMIN(Authority.ADMIN);

    private final String authority;


    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    // 여긴 왜 static final 로 하는지
    public static class Authority {
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}
