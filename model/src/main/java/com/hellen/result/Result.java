package com.hellen.result;

import com.hellen.enum_.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class Result<T> {

    private int code ;//返回码

    private T t;//返回数据

    private String message;//返回信息

    private static <T> Result<T> build(String message,int code,T t){
        return new Result<T>(code, t, message);
    }

    private static <T> Result<T> build(ResultCodeEnum resultCodeEnum,T t){
        return new Result<T>(resultCodeEnum.getCode(), t, resultCodeEnum.getMessage());
    }

    private static <T> Result<T> build(T t){
        return new Result<T>(ResultCodeEnum.SUCCESS.getCode(), t, ResultCodeEnum.SUCCESS.getMessage());
    }

    public static <T> Result<T> success(){
        return build(null);
    }

    public static <T> Result<T> success(T t){
        return build(t);
    }

    public static <T> Result<T> success(T t,String message){
        return build(message,ResultCodeEnum.SUCCESS.getCode(), t);
    }

    public static <T> Result<T> fail(T t,String message){
        return build(message,ResultCodeEnum.FAIL.getCode(), t);
    }

    public static <T> Result<T> fail(ResultCodeEnum resultCodeEnum){
        return build(resultCodeEnum.getMessage(),resultCodeEnum.getCode(), null);
    }

    public static <T> Result<T> fail(String message){
        return build(message,ResultCodeEnum.FAIL.getCode(), null);
    }
}
