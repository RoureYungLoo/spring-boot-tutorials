package com.xxx.springboot15redis.service;

import com.xxx.springboot15redis.entiry.User;

import java.util.List;

public interface UserService {
    User getUser(User user);

    void setUser(User user);

    void setUserHash(User user);
    User getUserHash(User user);

    void pushUserList(List<User> userList);

    List<User> getUserList();
}
