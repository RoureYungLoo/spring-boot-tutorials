package com.xxx.springboot09globalexception.exception;

import com.xxx.springboot09globalexception.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /* 参数缺失异常 */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        logger.error("缺少请求参数: {}", e.getMessage());
        return new JsonResult(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    /* 处理空指针异常 */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleNullPointerException(NullPointerException e) {
        logger.error("空指针异常: {}", e.getMessage());
        return new JsonResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage() + "老铁，空指针了");
    }

    /* 处理自定义业务异常 */
    @ExceptionHandler(BusinessException.class)
    public JsonResult handleBusinessException(BusinessException e) {
        logger.error("业务出现异常：{}", e.getMessage());
        return new JsonResult(Integer.parseInt(e.getCode()), e.getMessage());
    }


    /* 处理所有异常 */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult handleException(Exception e) {
        logger.error("系统异常: {}", e.getMessage());
        return new JsonResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage() + "系统异常");
    }

    /* 处理diy异常 */
    @ExceptionHandler(DiyException.class)
    public JsonResult handleDiyException(DiyException e) {
        logger.error("diyException: {}", e.getMessage());
        return new JsonResult(6969, e.getMessage());
    }
}
