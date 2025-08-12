package com.luruoyang.config;

import com.luruoyang.filter.JwtTokenOncePerRequestFilter;
import com.luruoyang.handler.AnonymousAuthenticationHandler;
import com.luruoyang.handler.CustomerAccessDeniedHandler;
import com.luruoyang.handler.LoginFailureHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author luruoyang
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  /**
   * 注入 BCryptPasswordEncoder
   *
   * @return BCryptPasswordEncoder
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * 登录时调用AuthenticationManager.authenticate执行一次校验
   */
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    // 添加自定义异常处理类
    http.exceptionHandling(configurer -> {
      configurer.accessDeniedHandler(customerAccessDeniedHandler) // 配置认证用户无权限访问资源的处理器
          .authenticationEntryPoint(anonymousAuthentication); // 配置匿名用户未认证的处理器
    });

    // 配置关闭csrf机制
    http.csrf(AbstractHttpConfigurer::disable);
    // 用户认证校验失败处理器
    http.formLogin(conf -> conf.failureHandler(loginFailureHandler));
    // STATELESS（无状态）：表示应用程序是无状态的，不创建会话
    http.sessionManagement(conf -> conf.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    // 配置放行路径
    http.authorizeHttpRequests(auth -> auth
        .requestMatchers(
            "/swagger-ui/**", // 放行Swagger相关路径
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/webjars/**",
            "/doc.html",
            "/admin/emp/login"  // 放行登录接口路径
        ).permitAll()
        .anyRequest().authenticated()
    );
    // 配置过滤器的执行顺序
    http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  // 自定义的用于认证的过滤器，进行jwt的校验操作
  @Autowired
  private final JwtTokenOncePerRequestFilter jwtTokenFilter;

  // 认证用户无权限访问资源的处理器
  @Autowired
  private final CustomerAccessDeniedHandler customerAccessDeniedHandler;

  // 客户端进行认证数据的提交时出现异常，或者是匿名用户访问受限资源的处理器
  @Autowired
  private final AnonymousAuthenticationHandler anonymousAuthentication;

  // 用户认证校验失败处理器
  @Autowired
  private final LoginFailureHandler loginFailureHandler;


}
