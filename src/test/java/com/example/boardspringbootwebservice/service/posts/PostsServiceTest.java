package com.example.boardspringbootwebservice.service.posts;

import com.example.boardspringbootwebservice.web.dto.PostsSaveRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostsServiceTest {

    @Autowired
    PostsService postsService;

    @Test
    @DisplayName("PostsService_TEST")
    void PostsServiceTest() {
        // given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .title("테스트")
                .content("테스트 중 입니다.")
                .author("홍길동")
                .build();

        // when
        Long saveId = postsService.save(dto);

        // then
        assertThat(saveId).isNotNull();
        assertThat(saveId).isEqualTo(1L);
    }
}