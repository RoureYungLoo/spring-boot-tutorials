package com.xxx.springboot11mybatis.service.impl;

import com.xxx.springboot11mybatis.entity.User;
import com.xxx.springboot11mybatis.mapper.UserMapper;
import com.xxx.springboot11mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int save(User user) {
        return userMapper.save(user);
    }

    @Override
    public int update(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public int delete(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public User getById(Long id) {
        return userMapper.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @Override
    public List<User> getByName(User user) {
        return userMapper.getByUsername(user);
    }
}
