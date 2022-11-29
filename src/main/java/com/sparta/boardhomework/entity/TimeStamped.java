package com.sparta.boardhomework.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// @MappedSuperclass 객체의 입장에서 공통 매핑정보가 필요할 때 사용한다.
// 부모 클래스에 선언하고 속성만 상속 받아서 사용하고 싶을 때 사용하는 어노테이션
// @Getter 는 롬복의 어노테이션 이며, 객체의 attribute(변수)를 반환
// @Setter 는 파라미터를 받아서 attribute 에 할당한다.
// @EntityListeners 는 엔티티의 변화를 감지하고 테이블의 데이터를 조작하는 일을 한다. DB 에 값을 넣거나 할때 그 객체
// 이 덕분에 Column 값이 수정되는 것에 대해서 반복된 코드를 추가를 할 필요가 없어졌다.
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class TimeStamped {

    // 게시글 작성시점 날짜 자동 업데이트
    @CreatedDate
    private LocalDateTime createdAt;

    // 게시글 수정시점 날짜 자동 업데이트
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
