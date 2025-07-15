package com.xxx.springboot05webmvc.controller;

import com.xxx.springboot05webmvc.dto.LoginDto;
import com.xxx.springboot05webmvc.dto.RegisterDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demos")
public class DemoController {

    //    @RequestMapping(method = RequestMethod.GET, value = "/get")
    @GetMapping("/get")
    public String get() {
        return "Hello GET";
    }

    //    @RequestMapping(method = RequestMethod.POST, value = "/post")
    @PostMapping("/post")
    public String post() {
        return "Hello POST";
    }

    //    @RequestMapping(method = RequestMethod.PUT, value = "/put")
    @PutMapping("/put")
    public String put() {
        return "Hello PUT";
    }

    //    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    @DeleteMapping("/delete")
    public String delete() {
        return "Hello DELETE";
    }

    //    @RequestMapping(method = RequestMethod.PATCH, value = "/patch")
    @PatchMapping("/patch")
    public String patch() {
        return "Hello PATCH";
    }

    @GetMapping("/path/{id}/{name}")
    public String getPathvariable(
            @PathVariable String id,
            @PathVariable("name") String username
    ) {
        return "Hello " + id + " " + username;
    }

    @GetMapping("/param")
    public String getParam(
            String name,
            @RequestParam Integer age,
            @RequestParam("email") String userEmail,
            String addr
    ) {
        return "Hello " + name + " " + age + " " + userEmail + " " + addr;
    }

    @GetMapping("/register")
    public String register(RegisterDto dto) {
        return "您的注册信息：" + dto;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        return "您的登录信息" + loginDto;
    }
}
