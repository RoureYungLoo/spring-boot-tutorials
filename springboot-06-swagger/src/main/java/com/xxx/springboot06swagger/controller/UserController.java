package com.xxx.springboot06swagger.controller;

import com.xxx.springboot06swagger.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"用户模块"},value = "用户控制器")
@RestController
@RequestMapping("/user")
public class UserController {

    @ApiOperation(value = "根据ID查询用户")
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") @ApiParam("查询参数-用户ID") Long id) {
        return new User(
                id,
                "张三",
                "123456",
                1,
                (short) 23,
                "zhangsan@qq.com",
                "18812341234",
                "zhengzhou"
        );
    }

    @ApiOperation("添加用户")
    @PostMapping
    public User addUser(@RequestBody @ApiParam("用户信息") User user) {
        user.setUid(System.currentTimeMillis());
        return user;
    }
}
