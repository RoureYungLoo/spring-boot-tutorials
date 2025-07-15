package com.xxx.springboot02jsonresult.utils;

import lombok.Data;

/* 封装统一返回格式 */
@Data
public class JsonResult<T> {
    private T data;
    private String code;
    private String msg;

    /* 成功，无数据返回 */
    public JsonResult() {
        this.data = null;
        this.code = "0";
        this.msg = "success";
    }

    public JsonResult(T data, String code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "data=" + data +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    /* 无数据返回 */
    public JsonResult(String code, String msg) {
        this.data = null;
        this.code = code;
        this.msg = msg;
    }

    /* 有数据返回 */
    public JsonResult(T data) {
        this.data = data;
        this.code = "0";
        this.msg = "success";
    }

    /* 有数据返回 */
    public JsonResult(String code, String msg, T data) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }
}
