package com.xxx.springboot19commonprojectarchitect.exception;

import jdk.nashorn.internal.objects.annotations.Getter;

public enum BusinessMsgEnum {
    /* ID 参数缺失*/
    ERR_ID_MISSING(4001, "id missing"),
    /* ID 不合法*/
    ERR_ID_ILLEGAL(4002, "id illegal"),
    /* 用户名已存在 */
    ERR_NAME_EXISTS(4003, "username exists"),
    /* 用户名密码不匹配 */
    ERR_PWD_ERR(4004, "password not match"),

    /* 其他业务异常 */
    ERR_OTHER(4444, "业务异常");

    private int code;
    private String msg;

    BusinessMsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
