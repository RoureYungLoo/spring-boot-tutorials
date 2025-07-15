package com.xxx.springboot07jdbctemplate.repository;

import com.xxx.springboot07jdbctemplate.entity.User;

import java.util.List;

public interface UserRepository {
    int save(User user);

    int update(User user);

    int delete(Long id);

    User findById(Long id);

    List<User> findAll();
}
