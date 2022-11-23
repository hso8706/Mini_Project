package com.example.diary.content.service;

import com.example.diary.content.entity.Content;
import com.example.diary.content.repository.ContentRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {
    //TODO DI
    private final ContentRepository repository;

    public ContentService(ContentRepository repository) {
        this.repository = repository;
    }
    //TODO createContent
    public Content createContent(Content content){
        return null;
    }
    //TODO patchContent
    public Content updateContent(Content content){
        return null;
    }
    //TODO findContent
    public Content findContent(long contentId){
        return null;
    }
    //TODO findAllContents
    public Page<Content> findAllContents(int page, int size){
        return null;
    }
    //TODO deleteContent
    public void deleteContent(long contentId){
//        Content findContent = findVerifiedContent(contentId);
//        repository.delete(findContent);
    }
    //TODO Validation; contentId를 이용해서 해당 Content가 DB에 있는지 확인
}
