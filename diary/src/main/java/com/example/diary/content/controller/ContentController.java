package com.example.diary.content.controller;

import com.example.diary.content.dto.ContentDto;
import com.example.diary.content.entity.Content;
import com.example.diary.content.mapper.ContentMapper;
import com.example.diary.content.service.ContentService;
import com.example.diary.dtoUtils.MultiResponseDto;
import com.example.diary.dtoUtils.SingleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/contents")
public class ContentController {
    private final ContentMapper mapper;
    private final ContentService contentService;

    public ContentController(ContentMapper mapper, ContentService contentService) {
        this.mapper = mapper;
        this.contentService = contentService;
    }

    @PostMapping
    public ResponseEntity postContent(@RequestBody ContentDto.Post postRequestBody){
        //TODO PostDTO to Entity
        Content content = mapper.contentPostToContent(postRequestBody);
        //TODO Entity to Service layer
        Content createContent = contentService.createContent(content);
        //TODO Entity to ResponseDTO
        ContentDto.Response response = mapper.contentToContentResponse(createContent);
        //TODO return by DataDto(ResponseDto ,HttpMethods)
        return new ResponseEntity(
                new SingleResponseDto<>(response),
                HttpStatus.CREATED);
    }

    @PatchMapping("/{content-id}")
    public ResponseEntity patchContent(@PathVariable("content-id") long contentId,
                                       @RequestBody ContentDto.Patch patchRequestBody){


        //TODO PatchDTO to Entity
        Content content = mapper.contentPatchToContent(patchRequestBody);
        //TODO Entity to Service layer
        Content updateContent = contentService.updateContent(content);
        //TODO Entity to ResponseDTO
        ContentDto.Response response = mapper.contentToContentResponse(updateContent);
        //TODO return by DataDto(ResponseDto ,HttpMethods)
        return new ResponseEntity(
                new SingleResponseDto<>(response),
                HttpStatus.OK);
    }

    //TODO contentId가 아닌 게시글 키워드로 조회하는 로직 구현 => Service 로직에서 구현
    @GetMapping("/{content-id}")
    public ResponseEntity getContent(@PathVariable("content-id") long contentId){
        Content content = contentService.findContent(contentId);
        ContentDto.Response response = mapper.contentToContentResponse(content);

        return new ResponseEntity(
                new SingleResponseDto<>(response),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllContents(@RequestParam int page,
                                         @RequestParam int size){
        //TODO 페이지네이션 구현
        Page<Content> pageContents = contentService.findAllContents(page, size); //쿼리로 제공되는 현재 페이지 쪽수(page)와 해당 페이지에 담길 데이터 갯수(size)로 Page 화 시킴
        List<Content> contents = pageContents.getContent(); // getContents : Page 가 상속하는 Slice 의 메서드, Page 를 List 로 변환, Dto 로 바꾸기 위함.
        List<ContentDto.Response> responses = mapper.contentListToContentResponseDtoList(contents);
        return new ResponseEntity(
                new MultiResponseDto(responses, pageContents),
                HttpStatus.OK
        );
    }
    @DeleteMapping("/{content-id}")
    public ResponseEntity deleteContent(@PathVariable("content-id") long contentId){
        contentService.deleteContent(contentId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
