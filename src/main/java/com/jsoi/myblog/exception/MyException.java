package com.jsoi.myblog.exception;

import lombok.Getter;

@Getter
public class MyException extends RuntimeException {
    private ErrorCode code;

    public MyException(ErrorCode code) {
        super(code.getDesc());
        this.code = code;
    }
}
