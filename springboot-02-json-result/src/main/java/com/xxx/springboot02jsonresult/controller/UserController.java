package com.xxx.springboot02jsonresult.controller;


import com.xxx.springboot02jsonresult.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/find")
    public Object getUserById() {
        User user = new User();
        user.setId(1001L);
        user.setUsername("zhangsan");
        user.setPassword("123456");
        return user;
    }

    @GetMapping("/list")
    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        for (int uid = 10001; uid < 10011; uid++) {
            User user = new User();
            user.setId((long) uid);
            user.setUsername("zhangsan" + uid);
            user.setPassword("123456" + uid);
            users.add(user);
        }
        return users;
    }

    @GetMapping("/map")
    public Map<String, Object> getUserMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = new User();
        user.setId(1001L);
        user.setUsername("zhangsan");
        user.setPassword("123456");
        map.put("用户信息", user);
        map.put("备注", "备注信息");
        map.put("博客地址", "https://www.baidu.com");
        map.put("UserListInfo", getUsers());
        map.put("nulltest",null);
        return map;
    }
}
