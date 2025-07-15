package com.xxx.springboot17shiro.controller;

import com.xxx.springboot17shiro.entity.User;
import com.xxx.springboot17shiro.service.UserService;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String saveUser(@RequestBody User user) {
        if (userService.saveUser(user)) {
            return "success";
        }
        return "failed";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        if (userService.deleteUser(id)) {
            return "success";
        }
        return "failed";
    }

    @PutMapping
    public String updateUser(@RequestBody User user) {
        if (userService.updateUser(user)) {
            return "success";
        }
        return "failed";
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getUserList();
    }

    @GetMapping("/role")
    public Set<String> getUserRoles(String username) {
        return userService.getRoles(username);
    }

    @GetMapping("/perm")
    public Set<String> getUserPerms(String username) {
        return userService.getPerms(username);
    }


}
