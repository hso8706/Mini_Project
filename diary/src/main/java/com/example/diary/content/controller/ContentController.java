package com.example.diary.content.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/contents")
public class ContentController {
    @PostMapping
    public ResponseEntity postContent(@RequestBody ContentDt.Post contentPostDto){
        //TODO PostDTO to Entity
        //TODO Entity to Service layer
        //TODO Entity to ResponseDTO
        //TODO return by DataDto(ResponseDto ,HttpMethods)
        return null;
    }
    @PatchMapping("/{content-id}")
    public ResponseEntity patchContent(@PathVariable("content-id") long contentId,
                                       @RequestBody ContentDto.Patch contentPatch){
        //TODO PatchDTO to Entity
        //TODO Entity to Service layer
        //TODO Entity to ResponseDTO
        //TODO return by DataDto(ResponseDto ,HttpMethods)
        return null;
    }
    //TODO contentId가 아닌 게시글 키워드로 조회하는 로직 구현
    @GetMapping("/{content-id}")
    public ResponseEntity getContent(){
        return null;
    }
    @GetMapping
    public ResponseEntity getContents(){
        //TODO 페이지네이션 구현
        return null;
    }
    @DeleteMapping("/{content-id}")
    public ResponseEntity deleteContent(){
        return null;
    }
}
