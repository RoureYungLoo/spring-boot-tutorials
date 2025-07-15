package com.xxx.springboot19commonprojectarchitect.entity;

import com.xxx.springboot19commonprojectarchitect.exception.BusinessMsgEnum;
import lombok.Data;

@Data
public class JsonResult<T> {
    private T data;
    private int code;
    private String msg;

    public JsonResult() {
        code = 0;
        msg = "success";
    }

    public JsonResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonResult(T data) {
        this.data = data;
        this.code = 0;
        this.msg = "success";
    }

    public JsonResult(T data, String msg) {
        this.data = data;
        this.code = 0;
        this.msg = msg;
    }

    public JsonResult(T data, int code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public JsonResult(BusinessMsgEnum msgEnum) {
        this.code = msgEnum.getCode();
        this.msg = msgEnum.getMsg();

    }
}
