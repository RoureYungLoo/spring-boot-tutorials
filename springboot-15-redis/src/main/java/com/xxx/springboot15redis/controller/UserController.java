package com.xxx.springboot15redis.controller;

import com.xxx.springboot15redis.entiry.User;
import com.xxx.springboot15redis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.LongSummaryStatistics;

@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @GetMapping
    public User getUser(@RequestBody User user) {
        return userService.getUser(user);
    }

    @PostMapping()
    public String setUser(@RequestBody User user) {
        userService.setUser(user);
        return "success";
    }

    @PostMapping("/hash")
    public String setUserHash(@RequestBody User user) {
        userService.setUserHash(user);
        return "success";
    }

    @GetMapping("/hash")
    public User getUserHash(@RequestBody User user) {
        return userService.getUserHash(user);
    }

    @PostMapping("/list")
    public String pushUserList(@RequestBody List<User> userList) {
        logger.info("userList: {}", userList);
        userService.pushUserList(userList);
        return "successfully pushed ";
    }

    @GetMapping("/list")
    public List<User> getUserList() {
        return userService.getUserList();
    }


}
