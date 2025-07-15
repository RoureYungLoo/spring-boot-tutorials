package com.xxx.springboot02jsonresult.controller;


import com.xxx.springboot02jsonresult.entity.User;
import com.xxx.springboot02jsonresult.utils.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user2")
public class UserController2 {

    @GetMapping("/find")
    public JsonResult<User> getUserById() {
        User user = new User();
        user.setId(1001L);
        user.setUsername("zhangsan");
        user.setPassword("123456");

        JsonResult<User> res = new JsonResult<>(user);
        return res;
    }

    @GetMapping("/list")
    public JsonResult<List> getUsers() {
        List<User> users = new ArrayList<User>();
        for (int uid = 10001; uid < 10011; uid++) {
            User user = new User();
            user.setId((long) uid);
            user.setUsername("zhangsan" + uid);
            user.setPassword("123456" + uid);
            users.add(user);
        }

        JsonResult<List> res = new JsonResult<>(users);
        return res;
    }

    @GetMapping("/map")
    public JsonResult<Map> getUserMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = new User();
        user.setId(1001L);
        user.setUsername("zhangsan");
        user.setPassword("123456");
        map.put("用户信息", user);
        map.put("备注", "备注信息");
        map.put("博客地址", "https://www.baidu.com");
        map.put("UserListInfo", getUsers());
        map.put("nulltest", null);

        JsonResult<Map> res = new JsonResult<>(map);
        return res;
    }
}
