package com.hellen.base.exception;

import com.hellen.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GloableException {

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public Result gloableException(Exception e) {
        return Result.fail("操作失败了" + e.getMessage());
    }
}
