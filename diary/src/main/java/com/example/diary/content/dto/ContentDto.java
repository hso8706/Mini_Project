package com.example.diary.content.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class ContentDto {
    //TODO Validation 필요
    @AllArgsConstructor //사용 : Test
    @Getter // 필수
    public static class Post{
        //TODO Content(Entity) 객체의 필드를 모두 받아야함.
        //예외 : 자동 생성되는 필드
        @NotEmpty(message = "글쓴이를 반드시 적어주시길 바랍니다.")
        private String writer;
        @NotEmpty(message = "텍스트 내용을 반드시 한 자 이상은 적어주시길 바랍니다.")
        private String text;
    }
    @AllArgsConstructor //사용 : Test
    @Getter// 필수
    public static class Patch{
        //TODO 글의 내용만 수정할 수 있게 설정
        @NotEmpty(message = "텍스트 내용을 반드시 한 자 이상은 적어주시길 바랍니다.")
        private String text;
    }
    @Getter// 필수
    public static class Response{
        //TODO Content(Entity) 객체의 필드를 모두 응답 메세지로 수신.
        //자동 생성 필드 포함
        private Long contentId;
        private String writer;
        private String text;
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime modifiedAt = LocalDateTime.now();

        public Response(Long contentId, String writer, String text) {
            this.contentId = contentId;
            this.writer = writer;
            this.text = text;
        }
    }
}
