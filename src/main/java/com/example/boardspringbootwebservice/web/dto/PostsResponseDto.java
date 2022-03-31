package com.example.boardspringbootwebservice.web.dto;

import com.example.boardspringbootwebservice.domain.posts.Posts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class PostsResponseDto {

    private final Long id; // id
    private final String title; // 제목
    private final String content; // 제목
    private final String author; // 작성자

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }

}
