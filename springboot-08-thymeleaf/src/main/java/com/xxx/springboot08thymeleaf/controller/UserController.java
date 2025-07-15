package com.xxx.springboot08thymeleaf.controller;

import com.xxx.springboot08thymeleaf.utils.Mock;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/list")
    public String getUsers(Model model) {

        model.addAttribute("userList", Mock.getUsers());

        return "index";
    }

    @GetMapping("/get")
    public String getById(Model model) {
//        int a= 1/0;

        model.addAttribute("user", Mock.getUser());

        return "userInfo";
    }

}
