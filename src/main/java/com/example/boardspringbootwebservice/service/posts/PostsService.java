package com.example.boardspringbootwebservice.service.posts;

import com.example.boardspringbootwebservice.domain.posts.Posts;
import com.example.boardspringbootwebservice.domain.posts.PostsRepository;
import com.example.boardspringbootwebservice.web.dto.PostsResponseDto;
import com.example.boardspringbootwebservice.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하는 게시글이 없습니다."));
        return new PostsResponseDto(entity);
    }
}
