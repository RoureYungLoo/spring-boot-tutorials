package com.xxx.springboot05webmvc.controller;

import com.xxx.springboot05webmvc.dto.LoginDto;
import com.xxx.springboot05webmvc.dto.RegisterDto;
import com.xxx.springboot05webmvc.entity.User;
import com.xxx.springboot05webmvc.service.UserService;
import com.xxx.springboot05webmvc.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
//@Tag(name = "用户模块")
public class UserController {

    @Autowired
    private UserService userService;

//    @Operation(summary = "根据ID查询")
//    @Parameter(name = "id", description = "用户ID", in = ParameterIn.PATH, required = true)
    @GetMapping("/{id}")
    public String queryUserById(@PathVariable("id") Long id) {
        return "Hello GET";
    }

//    @Operation(summary = "保存用户")
    @PostMapping
    public Result saveUser(@RequestBody User user) {
        userService.save(user);
        return Result.success();
    }

    @PutMapping("/put")
    public String put() {
        return "Hello PUT";
    }

    @DeleteMapping("/delete")
    public String delete() {
        return "Hello DELETE";
    }

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
