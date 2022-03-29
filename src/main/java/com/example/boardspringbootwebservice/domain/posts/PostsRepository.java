package com.example.boardspringbootwebservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository 를 상속하여 인터페이스를 생성해줍니다. 제네릭 타입으로는 <T, ID> 설정합니다.
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
