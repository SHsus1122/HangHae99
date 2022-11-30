package com.sparta.boardhomework.service;

import com.sparta.boardhomework.dto.BoardRequestDto;
import com.sparta.boardhomework.dto.BoardResponseDto;
import com.sparta.boardhomework.dto.DeleteRequestDto;
import com.sparta.boardhomework.dto.DeleteResponseDto;
import com.sparta.boardhomework.entity.Board;
import com.sparta.boardhomework.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// 참고링크 : https://velog.io/@developerjun0615/Spring-RequiredArgsConstructor-%EC%96%B4%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98%EC%9D%84-%EC%82%AC%EC%9A%A9%ED%95%9C-%EC%83%9D%EC%84%B1%EC%9E%90-%EC%A3%BC%EC%9E%85
// @RequiredArgsConstructor 은 lombok 을 사용하여 생성자 주입을 더 간단하게 해준다.
// final 이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
@Service
@RequiredArgsConstructor
public class BoardService {
    // 참고 링크 : https://velog.io/@kdhyo/JavaTransactional-Annotation-%EC%95%8C%EA%B3%A0-%EC%93%B0%EC%9E%90-26her30h
    // @Transactional 데이터베이스의 상태를 변경하는 작업 또는 한번에 수행되어야 하는 연산
    // 가장 큰 장점은 예외 발생시 rollback 처리를 자동으로 해준다.

    // @RequiredArgsConstructor 덕분에 생성자 자동 생성
    private final BoardRepository boardRepository;
    // 위 Repo 를 상수로 지정하는 이유
    // 전체 프로젝트에서 중복되서 생성되면 X 그래서 싱글톤으로 사용


    // 글 작성 부분
    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        boardRepository.save(board);
        return new BoardResponseDto(board);
    }

    // 모든 글 조회
    // 람다식을 이용해서 처리 (Stream) 반복 시작
    // 아래의 코드에서 사용된 .map 은 요소들을 특정조건에 해당하는 값으로 변환해 준다. 함수 실행
    // .collect 는 연산이 끝나고 반환해준다는 의미이다.
    // Collectors.toList 는 반환해 줄 때 리스트 타입으로 반환해준다는 의미이다.
    // 참고링크 : https://codechacha.com/ko/java8-convert-stream-to-list/
    // 참고링크 : https://dpdpwl.tistory.com/81
    public List<BoardResponseDto> getBoards() {
        List<Board> ListBoard = boardRepository.findAllByOrderByModifiedAtDesc();
        return ListBoard.stream()
                .map(board -> new BoardResponseDto(board))
                .collect(Collectors.toList());
    }
/*    @Transactional
    public List<BoardResponseDto> getBoards() {
        List<Board> boardList = boardRepository.findAllByOrderByModifiedAtDesc();
        List<BoardResponseDto> postResponseDto = new ArrayList<>();
        for (Board board : boardList) {
            BoardResponseDto boadrDto = new BoardResponseDto(board);
            postResponseDto.add(boadrDto);
        }
        return postResponseDto;
    }*/


    // 게시글 하나 조회하는 부분
/*    @Transactional
    public Optional<Board> readBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글 입니다.")
        );
        return boardRepository.findById(id);
    }*/
    public BoardResponseDto readBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글 입니다.")
        );
        BoardResponseDto boardResponseDto = new BoardResponseDto(board);
        return boardResponseDto;
    }

    // 게시글 수정
    @Transactional
    public BoardResponseDto update(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글 입니다.")
        );
        if (board.getPassword().equals(requestDto.getPassword())) {
            board.update(requestDto);
            boardRepository.save(board);
        }
        return new BoardResponseDto(board);
    }
/*    @Transactional
    public String update(Long id ,BoardRequestDto requestDto) {
        String str = "";
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글 입니다.")
        );
        if (!board.getPassword().equals(requestDto.getPassword())) {
            return str = "비밀번호 틀림";
        } else {
            board.update(requestDto);
            return str = "수정 완료";
        }
    }*/

    // 게시글 삭제
    @Transactional
    public DeleteResponseDto deleteBoard(Long id, DeleteRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글 입니다.")
        );

        DeleteResponseDto responseDto;

        if (!board.getPassword().equals(requestDto.getPassword())) {
            responseDto = new DeleteResponseDto("fail", false);
        } else {
            boardRepository.deleteById(id);
            responseDto = new DeleteResponseDto("success", true);
        }
        return responseDto;
    }
}
