package com.example.diary.dtoUtils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PageInfo {
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
