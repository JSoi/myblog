package com.jsoi.myblog.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String author;
    private String content;
    private Long postId;
}
