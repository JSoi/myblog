package com.jsoi.myblog.valdation;

import com.jsoi.myblog.dto.CommentRequestDto;
import com.jsoi.myblog.dto.PostRequestDto;
import com.jsoi.myblog.exception.ErrorCode;
import com.jsoi.myblog.exception.MyException;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    public static void validateComment(CommentRequestDto commentRequestDto) {
        if (commentRequestDto.getAuthor().equals("")) {
            throw new MyException(ErrorCode.EMPTY_COMMENT_AUTHOR);
        }
        if (commentRequestDto.getContent().equals("")) {
            throw new MyException(ErrorCode.EMPTY_COMMENT_CONTENT);
        }
    }

    public static void validatePost(PostRequestDto postRequestDto) {
        if (postRequestDto.getAuthor().equals("")) {
            throw new MyException(ErrorCode.EMPTY_POST_AUTHOR);
        }
        if (postRequestDto.getTitle().equals("")) {
            throw new MyException(ErrorCode.EMPTY_POST_TITLE);
        }
        if (postRequestDto.getContent().equals("")) {
            throw new MyException(ErrorCode.EMPTY_POST_CONTENT);
        }
    }
}
