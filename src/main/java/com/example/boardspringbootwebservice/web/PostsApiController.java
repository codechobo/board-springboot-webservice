package com.example.boardspringbootwebservice.web;

import com.example.boardspringbootwebservice.domain.posts.Posts;
import com.example.boardspringbootwebservice.service.posts.PostsService;
import com.example.boardspringbootwebservice.web.dto.PostsResponseDto;
import com.example.boardspringbootwebservice.web.dto.PostsSaveRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }
}
