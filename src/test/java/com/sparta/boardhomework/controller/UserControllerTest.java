package com.sparta.boardhomework.controller;

import com.sparta.boardhomework.dto.LoginRequestDto;
import com.sparta.boardhomework.dto.SignupRequestDto;
import com.sparta.boardhomework.entity.User;
import com.sparta.boardhomework.entity.UserRoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Nested
@DisplayName("회원이 요청한 계정 생성")
class UserControllerTest {

    private Long userId;
    private String username;
    private String password;
    private boolean admin;
    private String adminToken;

//    @BeforeEach
//    void setup() {
//        username = "tjgurtn11";
//        password = "Daabb1122";
//        admin = false;
//        adminToken = "";
//    }

    @Test
    @DisplayName("정상 케이스")
    void createUser_Normal() {

        SignupRequestDto signupRequestDto = new SignupRequestDto(
                username = "tjgurtn11",
                password = "DdAaCc1122",
                admin = false,
                adminToken = ""
        );

        User user = new User(signupRequestDto.getUsername(), signupRequestDto.getPassword(), UserRoleEnum.USER);

        assertNull(user.getUserId());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(UserRoleEnum.ADMIN, user.getRole());
    }
}