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

    @PostMapping("/{member-id}")
    public ResponseEntity postContent(@PathVariable("member-id") long memberId,
                                      @RequestBody ContentDto.Post postRequestBody){
        //TODO PostDTO to Entity
        Content content = mapper.contentPostToContent(postRequestBody);
        //TODO Entity to Service layer
        Content createContent = contentService.createContent(content);
        //TODO Entity to ResponseDTO
        ContentDto.Response response = mapper.contentToContentResponse(createContent);
        //TODO return by DataDto(ResponseDto ,HttpMethods)
        return new ResponseEntity(
                new SingleResponseDto<>(response), HttpStatus.CREATED);
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
                new SingleResponseDto<>(response), HttpStatus.OK);
    }

    //TODO contentId??? ?????? ????????? ???????????? ???????????? ?????? ?????? => Service ???????????? ??????
    @GetMapping("/{content-id}")
    public ResponseEntity getContent(@PathVariable("content-id") long contentId){
        Content content = contentService.findContent(contentId);
        ContentDto.Response response = mapper.contentToContentResponse(content);

        return new ResponseEntity(
                new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllContents(@RequestParam int page,
                                         @RequestParam int size){
        //TODO ?????????????????? ??????
        Page<Content> pageContents = contentService.findAllContents(page-1, size); //????????? ???????????? ?????? ????????? ??????(page)??? ?????? ???????????? ?????? ????????? ??????(size)??? Page ??? ??????
        List<Content> contents = pageContents.getContent(); // getContents : Page ??? ???????????? Slice ??? ?????????, Page ??? List ??? ??????, Dto ??? ????????? ??????.
        List<ContentDto.Response> responses = mapper.contentListToContentResponseDtoList(contents);
        return new ResponseEntity(
                new MultiResponseDto(responses, pageContents), HttpStatus.OK);
    }
    @DeleteMapping("/{content-id}")
    public ResponseEntity deleteContent(@PathVariable("content-id") long contentId){
        contentService.deleteContent(contentId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
