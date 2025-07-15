package com.xxx.springboot05webmvc.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private int code;
    private String msg;
    private Object data;

    private Result() {
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result success() {
        return new Result(200, "success");
    }

    public static Result success(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result fail(int code, String msg) {
        return new Result(code, msg);
    }

    public static Result fail() {
        return new Result(500, "fail");
    }
}
