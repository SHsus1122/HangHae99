package com.sparta.boardhomework.service;

import com.sparta.boardhomework.dto.LoginRequestDto;
import com.sparta.boardhomework.dto.SignupRequestDto;
import com.sparta.boardhomework.entity.User;
import com.sparta.boardhomework.entity.UserRoleEnum;
import com.sparta.boardhomework.exception.CustomException;
import com.sparta.boardhomework.exception.ErrorCode;
import com.sparta.boardhomework.jwt.JwtUtil;
import com.sparta.boardhomework.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    private static final String ADMIN_TOKEN = "HangHae99NoHangHae130Yes";

    // 회원가입
    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATED_USER);
        }

        UserRoleEnum role = UserRoleEnum.USER;

        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new CustomException(ErrorCode.INVALID_AUTH_TOKEN);
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, role);
        userRepository.save(user);
    }

//    @Transactional(readOnly = true)
//    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
//        String username = loginRequestDto.getUsername();
//        String password = loginRequestDto.getPassword();
//
//        User user = userRepository.findByUsername(username).orElseThrow(
//                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
//        );
//
////        if (!user.getPassword().equals(password)) {
////            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
////        }
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
//        }
//
//        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));
//    }

    // 로그인
    @Transactional(readOnly = true)
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER)
        );

        // 비밀번호 확인
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new CustomException(ErrorCode.WRONG_PASSWORD);
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));
        //add header로 헤더에 값 넣어주기 (키, 토큰)
    }
}
