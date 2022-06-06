package com.jsoi.myblog.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostRequestDto {
    private String title;
    private String author;
    private String content;
}
