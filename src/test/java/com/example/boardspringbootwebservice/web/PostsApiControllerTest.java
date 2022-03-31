package com.example.boardspringbootwebservice.web;

import com.example.boardspringbootwebservice.domain.posts.Posts;
import com.example.boardspringbootwebservice.domain.posts.PostsRepository;
import com.example.boardspringbootwebservice.web.dto.PostsSaveRequestDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
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
}