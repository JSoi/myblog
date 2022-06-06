package com.jsoi.myblog.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 에러가 발생했을 때 반환해줄 객체 (ResponseBody에서 사용될 것이므르 객체를 만들어 줬다.)
 */
@Getter
@AllArgsConstructor
public class ExceptionMessage {
    private String Message;
}
