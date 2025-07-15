package com.xxx.springboot09globalexception.exception;

import com.xxx.springboot09globalexception.BusinessMsgEnum;

/* 自定义异常 */
public class BusinessException extends RuntimeException {
    public static final long serialVersionUID = 1L;

    private String code;
    private String message;

    public BusinessException(BusinessMsgEnum businessMsgEnum) {
        this.code = businessMsgEnum.getCode();
        this.message = businessMsgEnum.getMsg();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
