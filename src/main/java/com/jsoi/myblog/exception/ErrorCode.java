package com.jsoi.myblog.exception;

public enum ErrorCode {
    EMPTY_COMMENT_CONTENT{
        @Override
        public String toString(){
            return "댓글의 내용을 입력해주세요";
        }
    },
    EMPTY_POST_COMMENT{
        @Override
        public String toString(){
            return "게시글의 내용을 입력해주세요";
        }
    },
    INVALID_POST_ID{
        @Override
        public String toString(){
            return "유효하지 않은 게시물 ID 입니다";
        }
    },
    EMPTY_CONTENT, INVALID_COMMENT_ID{
        @Override
        public String toString(){
            return "유효하지 않은 댓글 ID 입니다";
        }
    }
}
