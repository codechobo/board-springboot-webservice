package com.example.boardspringbootwebservice.service.posts;

import com.example.boardspringbootwebservice.domain.posts.Posts;
import com.example.boardspringbootwebservice.domain.posts.PostsRepository;
import com.example.boardspringbootwebservice.web.dto.PostsListResponseDto;
import com.example.boardspringbootwebservice.web.dto.PostsResponseDto;
import com.example.boardspringbootwebservice.web.dto.PostsSaveRequestDto;
import com.example.boardspringbootwebservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = getEntity(id);
        return new PostsResponseDto(entity);
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts entity = getEntity(id);
        entity.update(requestDto.getTitle(), requestDto.getContent());
        return entity.getId();
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts entity = getEntity(id);
        postsRepository.delete(entity);
    }

    private Posts getEntity(Long id) {
        return postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하는 게시글이 없습니다. id = " + id));
    }
}
