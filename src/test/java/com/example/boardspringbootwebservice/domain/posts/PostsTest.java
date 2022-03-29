package com.example.boardspringbootwebservice.domain.posts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PostsTest {

    @Test
    @DisplayName("Posts_Domain_TEST")
    void PostsTest() {
        // given
        String title = "테스트";
        String content = "테스트 중 입니다.";
        String author = "홍길동";

        // when
        Posts domain = Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        // then
        assertThat(domain.getTitle()).isEqualTo(title);
        assertThat(domain.getContent()).isEqualTo(content);
        assertThat(domain.getAuthor()).isEqualTo(author);
    }

}