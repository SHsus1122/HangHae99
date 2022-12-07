package com.sparta.boardhomework.controller;

import com.sparta.boardhomework.dto.*;
import com.sparta.boardhomework.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


// RestController 는 Controller 어노테이션에 ResponseBody 가 결합된 형태의 어노테이션
// 즉, 값 자체만을 반환하는 ResponseBody 의 기능이 기본적으로 들어가 있다.

// @RequiredArgsConstructor 는 Lombok 으로 스프링에서 DI(의존성 주입)의 방법 중에 생성자 주입을
// 임의의 코드 없이 자동으로 설정해주는 어노테이션 이다.
// 커밋 테스트
// 푸쉬 테스트
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService baBoardService;

    // 기본 사이트 접근시 페이지
    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    // 게시글 쓰기 API
    @PostMapping("/api/boards")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto, HttpServletRequest request) {
        return baBoardService.createBoard(requestDto, request);
    }

    // 게시글을 긁어오는 API
    @GetMapping("/api/boards")
    public List<BoardResponseDto> getBoard() {
        return baBoardService.getBoards();
    }

    // 게시글 선택해서 읽기
    @GetMapping("/api/boards/{id}")
    public BoardResponseDto readBoard(@PathVariable Long id) {
        return baBoardService.readBoard(id);
    }

    // 게시글 수정 API
    @PutMapping("/api/boards/{id}")
    public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto, HttpServletRequest request) {
        return baBoardService.update(id, requestDto, request);
    }

    // 게시글 삭제 API
    @DeleteMapping("/api/boards/{id}")
    public MsgResponseDto deleteBoard(@PathVariable Long id, HttpServletRequest request) {
        return baBoardService.deleteBoard(id, request);
    }
}
