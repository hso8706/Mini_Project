package com.example.diary.content.mapper;

import com.example.diary.content.dto.ContentDto;
import com.example.diary.content.entity.Content;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContentMapper {
    Content contentPostToContent(ContentDto.Post requestBody);
    Content contentPatchToContent(ContentDto.Patch requestBody);
    ContentDto.Response contentToContentResponse (Content content);
    List<ContentDto.Response> contentListToContentResponseDtoList (List<Content> contents);
}

/*
@Mapper : Mapper(Mapstruct) 를 사용하기 위한 애너테이션

 */
