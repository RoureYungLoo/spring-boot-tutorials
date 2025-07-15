package com.xxx.springboot10aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping
    public String hello(String name, Integer age) {

//        age = 1/0;

        return "hello user";
    }

}
