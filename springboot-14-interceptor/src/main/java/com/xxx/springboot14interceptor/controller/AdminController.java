package com.xxx.springboot14interceptor.controller;

import com.xxx.springboot14interceptor.annotation.CancelIntercepting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/login")
    @CancelIntercepting
    public String testCancelIntercepting() {
        return "admin login";
    }
}
