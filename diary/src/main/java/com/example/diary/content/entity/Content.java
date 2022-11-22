package com.example.diary.content.entity;

import org.springframework.data.domain.Auditable;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Content{
    //TODO contentId
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
