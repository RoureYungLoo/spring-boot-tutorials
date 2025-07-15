package com.xxx.springboot13listeners.controller;

import com.xxx.springboot13listeners.entity.User;
import com.xxx.springboot13listeners.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @GetMapping
    public User getUser() {
        return userService.getUSer();
    }


    @GetMapping("/app")
    public User getUserFromApplicationObj(HttpServletRequest request) {
        ServletContext application = request.getServletContext();
        return (User) application.getAttribute("user");

    }

    /* 监听application域对象 */
    @GetMapping("/total/{id}")
    public String getTotal(HttpServletRequest request) {
        Integer count = (Integer) request.getSession().getServletContext().getAttribute("count");
        return "当前在线人数：" + count;
    }


    /* 监听session */
    @GetMapping("/total2/{id}")
    public String getTotal2(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie;

        try {
            cookie = new Cookie("JSESSIONID", URLEncoder.encode(request.getSession().getId(), "UTF-8"));
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(cookie);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("/user/total2/id: {}", e.getMessage());
        }
        Integer count = (Integer) request.getSession().getServletContext().getAttribute("count");
        return "当前在线人数：" + count;
    }


    /* 监听request域对象 */
    @GetMapping("/req")
    public String getReq(HttpServletRequest request) {
        return (String) request.getAttribute("name");
    }

    /* 监听自定义事件 */
    @GetMapping("/publish-event")
    public User PublishEvent(HttpServletRequest request) {
        return userService.getUserAndPublishEvent();
    }

}
