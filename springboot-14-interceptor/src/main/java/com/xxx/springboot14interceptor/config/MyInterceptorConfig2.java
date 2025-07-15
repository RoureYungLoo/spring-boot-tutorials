package com.xxx.springboot14interceptor.config;

import com.xxx.springboot14interceptor.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/* 配置拦截器 */
@Configuration
public class MyInterceptorConfig2 implements
        WebMvcConfigurer {

    /* 拦截（除了静态资源外的）所有请求 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(new MyInterceptor()) /* 注册拦截器 */
                .addPathPatterns("/**") /* 拦截所有请求路径 */
                .excludePathPatterns("/**/*.img") /* 放行路径 */
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/register");
    }

    /* 放行静态资源 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
