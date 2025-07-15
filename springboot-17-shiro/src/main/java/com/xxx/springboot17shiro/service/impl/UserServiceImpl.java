package com.xxx.springboot17shiro.service.impl;

import com.xxx.springboot17shiro.entity.User;
import com.xxx.springboot17shiro.mapper.UserMapper;
import com.xxx.springboot17shiro.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean saveUser(User user) {
        int count = userMapper.saveUser(user);
        logger.info("saveUser: {}", user);
        return count > 0;
    }

    @Override
    public boolean deleteUser(int id) {
        return userMapper.delete(id) > 0;
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.updateUser(user) > 0;
    }

    @Override
    public User getUserById(int id) {
        return userMapper.getById(id);
    }

    @Override
    public List<User> getUserList() {
        return userMapper.getAll();
    }

    @Override
    public User getUserByName(String username) {
        return userMapper.getByName(username);
    }

    @Override
    public Set<String> getRoles(String username) {
        return userMapper.getRoles(username);
    }

    @Override
    public Set<String> getPerms(String username) {
        return userMapper.getPerms(username);
    }
}
