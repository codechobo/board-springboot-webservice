package com.example.boardspringbootwebservice.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 해당 엔티티를 상속한 엔티티의 속성 값도 엔티티에 적용
@EntityListeners(AuditingEntityListener.class) // Auditing 기능을 적용
public class BaseTimeEntity {

    @CreatedDate // 엔티티가 생성되어 저장될 때 시간이 자동 저장
    private LocalDateTime createdTime;

    @LastModifiedDate // 조회한 엔티티의 값을 변경할 때 시간이 자동 저장
    private LocalDateTime modifiedTime;
}
