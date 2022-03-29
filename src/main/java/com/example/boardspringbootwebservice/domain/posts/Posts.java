package com.example.boardspringbootwebservice.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // getter() 를 생성해 줍니다.
@NoArgsConstructor // 기본 생성자를 생성해 줍니다.
@Entity // 관계형 테이블과 링크될 클래스임을 정의합니다.
public class Posts {

    @Id // 엔티티의 PK를 설정해줍니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙을 설정 힙니다. 자동으로 id 값이 올라갑니다.
    private Long id; // id

    @Column(length = 500, nullable = false) // 해당 컬럼의 데이터 타입 길이를 500으로 설정하고 not null 제약을 설정합니다.
    private String title; // 제목

    @Column(columnDefinition = "TEXT", nullable = false) // 해당 컬럼의 데이터 타입을 TEXT 로 설정하고 not null 제약을 설정합니다.
    private String content; // 내용

    private String author; // 작성자

    @Builder // 빌더 패턴으로 생성
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
