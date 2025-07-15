package com.xxx.springboot17shiro.controller;

import com.xxx.springboot17shiro.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class DemoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    /* shiro认证接口示例 */
    @GetMapping("/admin")
    public String admin(HttpServletRequest request) {
        Object user = request.getSession().getAttribute("user");

        LOGGER.info("/user/admin: session user: {}", user);

        return "success";
    }

    @GetMapping("/student")
    public String student(HttpServletRequest request) {
        return "success";
    }

    @GetMapping("/teacher")
    public String teacher(HttpServletRequest request) {
        return "success";
    }


    @PostMapping("/login")
    public String login(User user, HttpServletRequest request) {
        LOGGER.info("登录表单提交：{}", user);

        // 根据用户名和密码创建token
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
// 获取subject认证主体
        Subject subject = SecurityUtils.getSubject();

        try {
            // 开始认证，这一步会跳到我们自定义的realm中
            subject.login(token);
            request.getSession().setAttribute("user", user);
            return "success";
        } catch (Exception e) {
            LOGGER.info("登录认证失败{}", e.toString());
            request.getSession().setAttribute("user", user);
            request.setAttribute("error", "用户名或密码错误");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "login";
    }
}
