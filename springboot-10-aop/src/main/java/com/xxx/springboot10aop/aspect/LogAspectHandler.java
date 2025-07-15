package com.xxx.springboot10aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Aspect
@Component
public class LogAspectHandler {
    private Logger logger = LoggerFactory.getLogger(LogAspectHandler.class);

    /* 切点表达式 */
    @Pointcut("execution(* com.xxx.springboot10aop.controller.*.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("--------- [前置通知] doBefore --------");
        Signature signature = joinPoint.getSignature();
        String typeName = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        Object[] args = joinPoint.getArgs();

        logger.info("即将执行 {}.{}(), 参数: {}", typeName, methodName, args);

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("请求url：{}, 请求ip: {}", request.getRequestURL().toString(), request.getRemoteAddr());
    }

    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint) {
        logger.info("--------- [后置（最终）通知] doAfter --------");
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();

        logger.info("方法 {} 已经执行完毕", methodName);
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("--------- [后置返回通知] doAfterReturning --------");

        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();

        logger.info("方法{}已经返回，返回值result：{}", methodName, result);

        /* 返回值增强 */
        result = result + "增强版" + new Date().getTime();

        logger.info("方法{}已经返回，增强后的返回值result：{}", methodName, result);
    }

    @AfterThrowing(value = "pointCut()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        logger.info("--------- [后置异常通知] doAfterThrowing --------");
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();

        logger.info("执行方法：{}出错，异常为：{}", methodName, ex.getMessage());
    }

    @Around("pointCut()")
    public void doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("--------- [环绕通知] doAround before --------");
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        Object[] args = joinPoint.getArgs();

        logger.info("即将执行方法{},参数{}", methodName, args);
        Object result = joinPoint.proceed(args);
        logger.info("已经从方法{}返回，返回值：{}", methodName, result);

        logger.info("--------- [环绕通知] doAround after --------");
    }

}
