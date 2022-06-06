package com.jsoi.myblog.exception;

import lombok.Getter;

@Getter
public class EmptyException extends RuntimeException {
    private ErrorCode code;

    public EmptyException(ErrorCode code) {
        super(code.getDesc());
        this.code = code;
    }
}
