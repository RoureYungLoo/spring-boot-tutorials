//package com.xxx.springboot20filter.filter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.annotation.WebListener;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Slf4j
//@WebFilter(filterName = "customFilter2", urlPatterns = "/*")
//public class CustomFilter2 implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        log.info("------ customFilter2 filter init --------");
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//
//        log.info("------ customFilter2  过滤请求的url:{}", req.getRequestURI());
//
//        /* 执行下一个过滤器 */
//        filterChain.doFilter(servletRequest, servletResponse);
//
//
//    }
//
//    @Override
//    public void destroy() {
//        log.info("------ customFilter2 filter destroy --------");
//
//        Filter.super.destroy();
//    }
//}
