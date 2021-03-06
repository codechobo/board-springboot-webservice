package com.example.boardspringbootwebservice.service.posts;

import com.example.boardspringbootwebservice.domain.posts.PostsRepository;
import com.example.boardspringbootwebservice.web.dto.PostsListResponseDto;
import com.example.boardspringbootwebservice.web.dto.PostsResponseDto;
import com.example.boardspringbootwebservice.web.dto.PostsSaveRequestDto;
import com.example.boardspringbootwebservice.web.dto.PostsUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class PostsServiceTest {

    @Autowired
    PostsService postsService;

    @Autowired
    PostsRepository postsRepository;

    @BeforeEach
    public void beforeEach() {
        PostsSaveRequestDto dto1 = PostsSaveRequestDto.builder()
                .title("테스트1")
                .content("테스트 중 입니다.1")
                .author("홍길동1")
                .build();

        PostsSaveRequestDto dto2 = PostsSaveRequestDto.builder()
                .title("테스트2")
                .content("테스트 중 입니다.2")
                .author("홍길동2")
                .build();

        postsService.save(dto1);
        postsService.save(dto2);
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

    @Test
    @DisplayName("Update_된다")
    void update() {
        // given
        Long findId = 1L;

        String expectedTitle = "테스트 수정";
        String expectedContent = "테스트 중 입니다. 수정";

        PostsUpdateRequestDto updateRequestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        // when
        postsService.update(findId, updateRequestDto);

        // then
        PostsResponseDto updatePosts = postsService.findById(1L);

        assertThat(updatePosts.getTitle()).isEqualTo(expectedTitle);
        assertThat(updatePosts.getContent()).isEqualTo(expectedContent);
    }

    @Test
    void findAllDesc() {
        // given && when
        List<PostsListResponseDto> responseDtos = postsService.findAllDesc();

        // then
        assertThat(responseDtos.isEmpty()).isFalse();
        assertThat(responseDtos.size()).isEqualTo(2);
        assertThat(responseDtos.get(0).getTitle()).isEqualTo("테스트2");
        assertThat(responseDtos.get(1).getTitle()).isEqualTo("테스트1");
    }

    @Test
    void 삭제된다() {
        // given
        Long id = 1L;

        // when
        postsService.delete(id);

        // then
        assertThatThrownBy(() -> postsService.findById(id))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하는 게시글이 없습니다. id = " + id);
    }
}