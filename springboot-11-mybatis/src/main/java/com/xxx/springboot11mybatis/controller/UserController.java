package com.xxx.springboot11mybatis.controller;


import com.xxx.springboot11mybatis.entity.User;
import com.xxx.springboot11mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String save(@RequestBody User user) {
        return userService.save(user) > 0 ? "success" : "fail";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        return userService.delete(id) > 0 ? "success" : "fail";
    }

    @PutMapping
    public String updateById(@RequestBody User user) {
        return userService.update(user) > 0 ? "success" : "fail";
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @PostMapping("/byname")
    public List<User> getByUsername(@RequestBody User user) {
        return userService.getByName(user);
    }
}
