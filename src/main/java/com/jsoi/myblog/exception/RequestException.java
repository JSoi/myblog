package com.jsoi.myblog.exception;

import com.jsoi.myblog.controller.CommentController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice(basePackages = "com.jsoi.myblog.controller")
public class RequestException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmptyException.class)
    protected ExceptionMessage handleDataException(EmptyException emptyException) {
        return new ExceptionMessage(emptyException.getCode().toString());
    }

}
