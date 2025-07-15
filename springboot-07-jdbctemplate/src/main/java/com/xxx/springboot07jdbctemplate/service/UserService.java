package com.xxx.springboot07jdbctemplate.service;

import com.xxx.springboot07jdbctemplate.entity.User;

import java.util.List;

public interface UserService {
    boolean save(User user);

    boolean update(User user);

    boolean delete(Long id);

    User getUserById(Long id);

    List<User> findAll();
}
