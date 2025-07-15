package com.xxx.springboot17shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/unauthorized")
    public String toUnauthorized(){
        return "unauthorized";
    }
}
