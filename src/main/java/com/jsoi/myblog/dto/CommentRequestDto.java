package com.jsoi.myblog.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {

    private final String author;
    private final String content;

    public CommentRequestDto(String author, String content) {

        this.author = author;
        this.content = content;
    }


}
