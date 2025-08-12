package com.luruoyang.filter;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.luruoyang.constants.JwtClaimsConstant;
import com.luruoyang.exception.CustomerAuthenticationException;
import com.luruoyang.properties.JwtProperties;
import com.luruoyang.user.EmpLogin;
import com.luruoyang.utils.BaseContext;
import com.luruoyang.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @description 每次请求的 Security 过滤类。执行jwt有效性检查，如果失败，不会设置 SecurityContextHolder 信息，会进入 AuthenticationEntryPoint
 */
// 每一个servlet请求,只执行一次
@Component
@Slf4j
public class JwtTokenOncePerRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProperties jwtProperties; // JWT相关属性配置类

    @Autowired
    private RedisUtil redisUtil; // Redis工具类

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    // 添加白名单路径列表
    private final String[] whitelist = {
            "/admin/emp/login",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/webjars/**",
            "/doc.html"
    };

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. 判断当前请求是否在白名单中
        String uri = request.getRequestURI();
        if (isWhitelisted(uri)) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            // 2. 校验token
            this.validateToken(request);
        } catch (AuthenticationException e) {
            loginFailureHandler.onAuthenticationFailure(request, response, e); // 处理登录失败的异常
            return;
        }
        filterChain.doFilter(request, response);
    }

    // 判断请求路径是否在白名单中
    private boolean isWhitelisted(String uri) {
        for (String pattern : whitelist) {
            if (pattern.endsWith("/**")) {
                // 处理通配符路径
                String basePattern = pattern.substring(0, pattern.length() - 3);
                if (uri.startsWith(basePattern)) {
                    return true;
                }
            } else if (pattern.equals(uri)) {
                // 精确匹配
                return true;
            }
        }
        return false;
    }

    // 校验token
    private void validateToken(HttpServletRequest request) {
        // 说明：登录了，再次请求其他需要认证的资源
        String token = request.getHeader("Authorization");
        if (ObjectUtils.isEmpty(token)) { // header没有token
            token = request.getParameter("Authorization");
        }
        if (ObjectUtils.isEmpty(token)) {
            throw new CustomerAuthenticationException("token为空");
        }
        // redis进行校验
        if (!redisUtil.hasKey("token_" + token)) {
            throw new CustomerAuthenticationException("token已过期");
        }
        // 校验token
        EmpLogin empLogin;
        try {
            log.info("jwt校验:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getSecretKey(), token);
            String loginUserString = claims.get(JwtClaimsConstant.EMP_LOGIN).toString(); //JwtClaimsConstant.EMP_LOGIN就是一个常量字符串“empLogin”
            // 把json字符串转为对象
            empLogin = JSON.parseObject(loginUserString, EmpLogin.class);
            log.info("当前员工id：{}", empLogin.getEmp().getId());
            BaseContext.setCurrentId(empLogin.getEmp().getId());
        } catch (Exception ex) {
            throw new CustomerAuthenticationException("token校验失败");
        }
        BaseContext.setCurrentId(empLogin.getEmp().getId());
        // 把校验后的用户信息再次放入到SpringSecurity的上下文中
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(empLogin, null,empLogin.getAuthorities()); // 已认证的 Authentication 对象，包含用户的权限信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println(empLogin.getAuthorities());
    }
}
