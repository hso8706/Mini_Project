package com.example.diary.content.entity;

import org.springframework.data.domain.Auditable;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Content{
    //TODO contentId
    private long contentId;
    //TODO writer
    private String writer;
    //TODO text
    private String text;
    //TODO createdAt
    private LocalDateTime createdAt = LocalDateTime.now();
    //TODO modifiedAt
    private LocalDateTime modifiedAt = LocalDateTime.now();
}
