package com.example.boardspringbootwebservice.web.dto;

import com.example.boardspringbootwebservice.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {

    private final Long id;
    private final String title;
    private final String author;
    private final LocalDateTime modifiedTime;

    @Builder
    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedTime = entity.getModifiedTime();
    }
}
