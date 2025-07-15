package com.xxx.springboot09globalexception;


/* 异常信息枚举 */
public enum BusinessMsgEnum {
    PARAM_ERROR("101", "参数异常"),
    TIME_OUT("102", "超时"),
    UID_ERROR("103", "UID 错误");
    private String code;
    private String msg;

    BusinessMsgEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
