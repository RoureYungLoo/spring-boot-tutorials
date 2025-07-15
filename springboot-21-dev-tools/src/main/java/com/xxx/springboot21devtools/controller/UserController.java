package com.xxx.springboot21devtools.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{id}")
    public String hello(@PathVariable("id") Integer id) {
        log.info(" /user/{}", id);
        return "devtools hello user " + id;
    }
}
