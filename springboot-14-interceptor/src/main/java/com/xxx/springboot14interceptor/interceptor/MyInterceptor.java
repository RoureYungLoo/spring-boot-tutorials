package com.xxx.springboot14interceptor.interceptor;

import com.xxx.springboot14interceptor.annotation.CancelIntercepting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/* 定义拦截器 */
public class MyInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        if (handler instanceof HandlerMethod) {
            /* 匹配到Controller，执行controller之前 */
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            logger.info("----------- 拦截到方法：{} ,该方法即将执行 ----------------", method.getName());

            /* 自定义取消拦截注解处理 */
            CancelIntercepting cancelIntercepting = method.getAnnotation(CancelIntercepting.class);
            if (cancelIntercepting != null) {
                logger.info("------- 方法{}带有注解 CancelIntercepting ，取消拦截------", method.getName());
                return true; // 放行，取消拦截
            }

            /*=========================================*/
            /* 登录拦截器 */
            String token = request.getHeader("token");
            if (token == null || token.equals("")) {
                logger.info("用户未登录");
                return false;
            }
            /*=========================================*/



            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if (handler instanceof HandlerMethod) {

            HandlerMethod handlerMethod = (HandlerMethod) handler;

            Method method = handlerMethod.getMethod();
        }

        /* 执行controller之后，视图渲染前 */
        logger.info("----------- 方法{}已经调用，尚未渲染视图 -----------");


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        /* 请求处理完后执行 */
        logger.info("----------- 请求处理完毕 ----------");
    }
}
