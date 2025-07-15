//package com.xxx.springboot20filter.filter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Slf4j
//@Component
//public class CustomFilter1 implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        log.info("------ filter init --------");
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//
//        log.info("------ 过滤请求的url:{}", req.getRequestURI());
//
//        /* 执行下一个过滤器 */
//        filterChain.doFilter(servletRequest, servletResponse);
//
//
//    }
//
//    @Override
//    public void destroy() {
//        log.info("------ filter destroy --------");
//
//        Filter.super.destroy();
//    }
//}
