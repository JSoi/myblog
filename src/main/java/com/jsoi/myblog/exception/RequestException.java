package com.jsoi.myblog.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "com.jsoi.myblog.controller") // AOP 타겟이 되는 패키지를 설정
public class RequestException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MyException.class)
    protected ExceptionMessage handleDataException(MyException myException) {
        return new ExceptionMessage(myException.getCode().getDesc());
    }

}
