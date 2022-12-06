package com.sparta.boardhomework.controller;

import com.sparta.boardhomework.dto.LoginRequestDto;
import com.sparta.boardhomework.dto.MsgResponseDto;
import com.sparta.boardhomework.dto.SignupRequestDto;
import com.sparta.boardhomework.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

/*    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }*/

    @GetMapping("/login")
    public ModelAndView loginpage() {
        return new ModelAndView("login");
    }

    @GetMapping("/signup")
    public ModelAndView signupPage() {
        return new ModelAndView("signup");
    }

    @ResponseBody
    @PostMapping("/signup")
    public ResponseEntity<MsgResponseDto> signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);

        // builder 패턴
        // 순서 상관없이 사용이 가능하다.
        return ResponseEntity.ok(MsgResponseDto.builder()
                .statusCode(HttpStatus.OK.value())
                .msg("회원가입 완료")
                .build());

//        return ResponseEntity.ok(new MsgResponseDto("회원 가입 완료", HttpStatus.OK.value()));
    }

    @ResponseBody
    @PostMapping("/login")
    public MsgResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return new MsgResponseDto("로그인 성공", HttpStatus.OK.value());
    }
}
