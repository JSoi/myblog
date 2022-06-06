package com.jsoi.myblog.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 에러 코드 열거형 - 해당 에러에 맞게 메시지를 반환해주고자 한다
 */
@Getter
public enum ErrorCode {
    EMPTY_COMMENT_CONTENT("댓글의 내용을 입력해주세요"),
    EMPTY_POST_COMMENT("게시글의 내용을 입력해주세요"),
    INVALID_POST_ID("유효하지 않은 게시물 ID 입니다"),
    INVALID_COMMENT_ID("유효하지 않은 댓글 ID 입니다");
    private final String desc;

    ErrorCode(String desc) {
        this.desc = desc;
    }
}
