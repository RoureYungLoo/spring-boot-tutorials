package com.xxx.springboot17shiro.service;

import com.xxx.springboot17shiro.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    boolean saveUser(User user);

    boolean deleteUser(int id);

    boolean updateUser(User user);

    User getUserById(int id);

    List<User> getUserList();

    User getUserByName(String username);
    Set<String> getRoles(String username);
    Set<String> getPerms(String username);
}
