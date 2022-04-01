package com.example.boardspringbootwebservice.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository; // bean 주입을 받아 사용 합니다.

    @AfterEach
    public void before() {
        postsRepository.deleteAll();
    }

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

    @Test
    @DisplayName("Base_Entity_TEST")
    void Base_Entity_TEST() {
        // given
        LocalDateTime now = LocalDateTime
                .of(2022, 4, 1, 0, 0, 0);

        postsRepository.save(Posts.builder()
                .title("테스트")
                .content("테스트 중 입니다.")
                .author("홍길동")
                .build()
        );

        // when
        List<Posts> all = postsRepository.findAll();

        // then
        assertThat(all.get(0).getCreatedTime()).isAfter(now);
        assertThat(all.get(0).getModifiedTime()).isAfter(now);
    }

}