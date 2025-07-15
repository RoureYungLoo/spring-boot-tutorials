package com.xxx.springboot19commonprojectarchitect.exception;

import com.xxx.springboot19commonprojectarchitect.entity.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public JsonResult exceptionHandler(Exception ex) {
        LOGGER.info("系统异常：{}, {}", 500, ex.getMessage());
        return new JsonResult(500, "系统异常");
    }
}
