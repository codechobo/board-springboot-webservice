package com.example.boardspringbootwebservice.web.dto;

import com.example.boardspringbootwebservice.domain.posts.Posts;
import com.example.boardspringbootwebservice.domain.posts.PostsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostsResponseDtoTest {

    @Autowired
    PostsRepository repository;

    @BeforeEach
    public void beforeEach() {
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title("테스트")
                .content("테스트 중 입니다.")
                .author("홍길동")
                .build();

        repository.save(requestDto.toEntity());
    }

    @Test
    @DisplayName("ResponseEntity_TEST")
    void PostsResponseDtoTest() {
        // given
        Long expectId = 1L;
        String expectTitle = "테스트";
        String expectContent = "테스트 중 입니다.";
        String expectAuthor = "홍길동";

        // when
        Posts posts = repository.findById(expectId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않아요!!"));

        PostsResponseDto responseDto = new PostsResponseDto(posts);

        // then
        assertThat(responseDto.getId()).isEqualTo(expectId);
        assertThat(responseDto.getTitle()).isEqualTo(expectTitle);
        assertThat(responseDto.getContent()).isEqualTo(expectContent);
        assertThat(responseDto.getAuthor()).isEqualTo(expectAuthor);
    }

}