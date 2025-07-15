package com.xxx.springboot09globalexception.utils;

public class JsonResult {
    private Integer code;
    private String msg;

    public JsonResult() {
        this.code = 200;
        this.msg = "success";
    }

    public JsonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
