package com.example.boardspringbootwebservice.service.posts;

import com.example.boardspringbootwebservice.domain.posts.PostsRepository;
import com.example.boardspringbootwebservice.web.dto.PostsResponseDto;
import com.example.boardspringbootwebservice.web.dto.PostsSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostsServiceTest {

    @Autowired
    PostsService postsService;

    @Autowired
    PostsRepository postsRepository;

    @BeforeEach
    public void beforeEach() {
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .title("테스트1")
                .content("테스트 중 입니다.1")
                .author("홍길동1")
                .build();

        postsService.save(dto);
    }

    @AfterEach
    public void afterEach() {
        postsRepository.deleteAll();
    }


    @Test
    @DisplayName("PostsService_TEST")
    void save() {
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

    @Test
    @DisplayName("FindById_TEST")
    void findById() {
        // given
        Long expectId = 1L;
        String expectTitle = "테스트1";
        String expectContent = "테스트 중 입니다.1";
        String expectAuthor = "홍길동1";

        // when
        PostsResponseDto responseDto = postsService.findById(expectId);

        // then
        assertThat(responseDto.getId()).isEqualTo(expectId);
        assertThat(responseDto.getTitle()).isEqualTo(expectTitle);
        assertThat(responseDto.getContent()).isEqualTo(expectContent);
        assertThat(responseDto.getAuthor()).isEqualTo(expectAuthor);
    }
}