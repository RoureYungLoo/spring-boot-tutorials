package com.xxx.springboot07jdbctemplate.controller;

import com.xxx.springboot07jdbctemplate.entity.User;
import com.xxx.springboot07jdbctemplate.service.UserService;
import com.xxx.springboot07jdbctemplate.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Result save(@RequestBody User user) {
        if (userService.save(user)) {
            return Result.success();
        } else {
            return Result.fail("保存失败");
        }
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id) {
        if (userService.delete(id)) {
            return Result.success();
        } else {
            return Result.fail("删除失败");
        }
    }

    @PutMapping
    public Result update(@RequestBody User user) {
        if (userService.update(user)) {
            return Result.success();
        } else {
            return Result.fail("更新失败");
        }
    }

    @GetMapping
    public Result findAll() {
        List<User> users = userService.findAll();
        return Result.success(users);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return Result.success(user);
    }
}
