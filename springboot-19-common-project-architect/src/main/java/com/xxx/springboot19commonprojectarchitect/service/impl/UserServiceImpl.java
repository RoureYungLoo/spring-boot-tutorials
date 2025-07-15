package com.xxx.springboot19commonprojectarchitect.service.impl;

import com.xxx.springboot19commonprojectarchitect.entity.User;
import com.xxx.springboot19commonprojectarchitect.mapper.UserMapper;
import com.xxx.springboot19commonprojectarchitect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;


    @Override
    public User getById(int id) {
        return userMapper.findById(id);
    }

    @Override
    public List<User> getAll() {
        return userMapper.findAll();
    }
}
