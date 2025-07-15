package com.xxx.springboot07jdbctemplate.utils;

public class Result<T> {
    private int code;
    private String msg;
    private T data;

    private Result() {
    }

    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /* 成功，无数据返回 */
    public static <T> Result<T> success(T data) {
        return new Result<T>(200, "success", data);
    }

    /* 成功，有数据返回 */
    public static <T> Result<T> success() {
        return new Result<T>(200, "success", null);
    }


    /* 失败，无数据返回 */
    public static <T> Result<T> fail(String msg) {
        return new Result<T>(200, msg, null);
    }

    /* 失败，有数据返回 */
    public static <T> Result<T> fail(String msg, T data) {
        return new Result<T>(200, msg, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
