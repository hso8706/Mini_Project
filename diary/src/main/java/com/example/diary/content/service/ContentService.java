package com.example.diary.content.service;

import com.example.diary.content.entity.Content;
import com.example.diary.content.repository.ContentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ContentService {
    //TODO DI
    private final ContentRepository repository;
    public ContentService(ContentRepository repository) {
        this.repository = repository;
    }

    //TODO createContent
    public Content createContent(Content content){
        //TODO 글이 저장됨. (현재는 저자도 같이 저장되게 구현)
        //TODO 이후에 저자(member)를 따로 구현하여 validation 활용할 것.
        Content savedContent = repository.save(content);
        return savedContent;

    }
    //TODO patchContent
    public Content updateContent(Content content){
        Content savedContent = repository.save(content);
        return savedContent;
    }
    //TODO findContent; 나중에 저자로 글 찾는 것으로 수정될 예정.
    public Content findContent(long contentId){
        Optional<Content> optionalContent = repository.findById(contentId);
        Content content = optionalContent.orElseThrow(); //.Optional.orElseThrow() : Optional 을 벗겨주는 메서드. 값이 Null이면, NoSuchElementException 발생

        return content;
    }
    //TODO findAllContents
    public Page<Content> findAllContents(int page, int size){
        return repository.findAll(PageRequest.of(page, size, Sort.by("contentId").descending()));
    }
    //TODO deleteContent
    public void deleteContent(long contentId){
        Optional<Content> optionalContent = repository.findById(contentId);
        Content content = optionalContent.orElseThrow();
        repository.delete(content);
    }
    //TODO Validation; contentId를 이용해서 해당 Content가 DB에 있는지 확인
}
