package com.example.boardspringbootwebservice.domain.posts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository; // bean 주입을 받아 사용 합니다.

    @Test
    @DisplayName("Repository_TEST")
    void PostsRepositoryTest() {
        // given
        String title = "테스트";
        String content = "테스트 중 입니다.";
        String author = "홍길동";

        postsRepository.save(
                Posts.builder()
                        .title(title)
                        .content(content)
                        .author(author)
                        .build()
        );

        // when
        List<Posts> postsRepositoryAll = postsRepository.findAll(); // 저장된 인스턴스의 값들을 List 타입으로 받습니다.

        // then
        assertThat(postsRepositoryAll.get(0).getTitle()).isEqualTo(title);
        assertThat(postsRepositoryAll.get(0).getContent()).isEqualTo(content);
        assertThat(postsRepositoryAll.get(0).getAuthor()).isEqualTo(author);
    }

}