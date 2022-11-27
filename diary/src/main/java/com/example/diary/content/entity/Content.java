package com.example.diary.content.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Auditable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
//@Entity // Id 생성 필수
public class Content{
    //TODO contentId
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentId; // repository 에서 사용하기 위해 래퍼클래스 사용, 근데 primary 를 쓰면 어차피 여러모로 불편함.
    //TODO writer
    private String writer;
    //TODO text
    private String text;
    //TODO createdAt
    private LocalDateTime createdAt = LocalDateTime.now();
    //TODO modifiedAt
    private LocalDateTime modifiedAt = LocalDateTime.now();
}
