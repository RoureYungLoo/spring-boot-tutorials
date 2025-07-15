package com.xxx.springboot15redis.service.impl;

import com.alibaba.fastjson.JSON;
import com.xxx.springboot15redis.entiry.User;
import com.xxx.springboot15redis.redis.RedisService;
import com.xxx.springboot15redis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private RedisService redisService;

    @Override
    public User getUser(User user) {

        String userInfoStr = redisService.get("" + user.getId());
        logger.info("userInfo: {}", userInfoStr);
        return JSON.parseObject(userInfoStr, User.class);
    }

    @Override
    public void setUser(User user) {
        redisService.set("" + user.getId(), JSON.toJSONString(user));
    }

    @Override
    public void setUserHash(User user) {
        String hashKey = "userInfoHash" + user.getId();
        redisService.setHash(hashKey, "id", String.valueOf(user.getId()));
        redisService.setHash(hashKey, "name", user.getName());
        redisService.setHash(hashKey, "age", String.valueOf(user.getAge()));
        redisService.setHash(hashKey, "address", user.getAddress());
        redisService.setHash(hashKey, "birthday", String.valueOf(user.getBirthday()));
    }

    @Override
    public User getUserHash(User user) {
        String hashKey = "userInfoHash" + user.getId();
        return new User(
                (Integer.parseInt(redisService.getHash(hashKey, "id")))
                , ((String) redisService.getHash(hashKey, "name"))
                , (Integer.parseInt(redisService.getHash(hashKey, "age")))
                , ((String) redisService.getHash(hashKey, "address"))
                , (new Date(redisService.getHash(hashKey, "birthday"))));

    }

    @Override
    public void pushUserList(List<User> userList) {
        userList.forEach(
                user -> {
                    redisService.leftPush("userList", JSON.toJSONString(user));
                }
        );
    }

    @Override
    public List<User> getUserList() {
        List<String> userStrList = redisService.range("userList", 0, -1);
        logger.info("userStrList: {}", userStrList);
        List<User> userList = new ArrayList<>();
        userStrList.forEach(
                userStr -> {
                    logger.info("userStr: {}", userStr);
                    userList.add(JSON.parseObject(userStr, User.class));
                }
        );
        return userList;
    }
}
