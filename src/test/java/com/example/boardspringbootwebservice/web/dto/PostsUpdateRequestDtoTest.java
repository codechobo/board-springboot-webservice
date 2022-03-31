package com.example.boardspringbootwebservice.web.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PostsUpdateRequestDtoTest {

    @Test
    @DisplayName("PostsUpdateRequestDto_TEST")
    void PostsUpdateRequestDtoTest() {
        // given
        String expectedTitle = "테스트";
        String expectedContent = "테스트 중 입니다.";

        // when
        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title("테스트")
                .content("테스트 중 입니다.")
                .build();

        // then
        assertThat(requestDto.getTitle()).isEqualTo(expectedTitle);
        assertThat(requestDto.getContent()).isEqualTo(expectedContent);
    }

}