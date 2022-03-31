package com.example.boardspringbootwebservice.web;

import com.example.boardspringbootwebservice.domain.posts.Posts;
import com.example.boardspringbootwebservice.domain.posts.PostsRepository;
import com.example.boardspringbootwebservice.web.dto.PostsSaveRequestDto;
import com.example.boardspringbootwebservice.web.dto.PostsUpdateRequestDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @BeforeEach
    public void beforeEach() {
        postsRepository.save(PostsSaveRequestDto.builder()
                .title("테스트1")
                .content("테스트 중 입니다.1")
                .author("홍길동1")
                .build()
                .toEntity());
    }

    @AfterEach
    public void afterEach() {
        postsRepository.deleteAll();
    }

    @Test
    @DisplayName("Posts_등록_TEST")
    void save() {
        // given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .title("테스트")
                .content("테스트 중 입니다.")
                .author("홍길동")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, dto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0)).isNotNull();
        assertThat(all.get(0).getTitle()).isEqualTo(dto.getTitle());
        assertThat(all.get(0).getContent()).isEqualTo(dto.getContent());
        assertThat(all.get(0).getAuthor()).isEqualTo(dto.getAuthor());
    }

    @Test
    @DisplayName("findByID_조회_TEST")
    void findById() {
        // given
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title("테스트")
                .content("테스트 중 입니다.")
                .author("홍길동")
                .build();

        Posts savedPosts = postsRepository.save(requestDto.toEntity());

        Long findId = 1L;
        String url = "http://localhost:" + port + "/api/v1/posts/" + findId;


        HttpEntity<PostsSaveRequestDto> postsSaveRequestDtoHttpEntity =
                new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Posts> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, postsSaveRequestDtoHttpEntity, Posts.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getTitle()).isEqualTo(requestDto.getTitle());
        assertThat(responseEntity.getBody().getContent()).isEqualTo(requestDto.getContent());
        assertThat(responseEntity.getBody().getAuthor()).isEqualTo(requestDto.getAuthor());
    }

    @Test
    @DisplayName("Update_된다!!!!")
    void update() {
        // given
        Posts posts = postsRepository
                .findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않습니다."));

        Long updatedId = posts.getId();
        String expectedTitle = "테스트2";
        String expectedContent = "테스트 중 입니다.2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();
        String url = "http://localhost:" + port + "/api/v1/posts/" + updatedId;

        HttpEntity<PostsUpdateRequestDto> postsUpdateRequestDtoHttpEntity =
                new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity =
                restTemplate.exchange(url, HttpMethod.PUT, postsUpdateRequestDtoHttpEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

    }
}