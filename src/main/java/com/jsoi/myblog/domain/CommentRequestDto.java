package com.jsoi.myblog.domain;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String author;
    private String content;
}
