package com.xxx.springboot19commonprojectarchitect.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xxx.springboot19commonprojectarchitect.entity.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BusinessExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessErrorException.class);

    @ExceptionHandler(BusinessErrorException.class)
    public JsonResult businessExceptionHandler(BusinessErrorException be) {
        LOGGER.info("业务异常：{}, {}", be.getCode(), be.getMessage());
        return new JsonResult(be.getCode(), be.getMessage());

    }
}
