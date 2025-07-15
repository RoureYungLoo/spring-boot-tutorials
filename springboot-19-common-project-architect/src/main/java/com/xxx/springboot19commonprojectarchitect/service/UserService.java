package com.xxx.springboot19commonprojectarchitect.service;

import com.xxx.springboot19commonprojectarchitect.entity.User;

import java.util.List;

public interface UserService {

    User getById(int id);

    List<User> getAll();
}
