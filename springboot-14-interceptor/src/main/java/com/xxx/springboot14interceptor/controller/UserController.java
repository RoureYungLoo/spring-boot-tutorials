package com.xxx.springboot14interceptor.controller;

import com.xxx.springboot14interceptor.annotation.CancelIntercepting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String testInterceptor() {
        return "Hello World";
    }

    @GetMapping("/login")
    public String login() {
        return "login success";
    }

    @GetMapping("/register")
    public String register() {
        return "register success";
    }

    @GetMapping("/cancel-interceptor")
    @CancelIntercepting
    public String testCancelIntercepting() {
        return "cancel intercepting";
    }
}
