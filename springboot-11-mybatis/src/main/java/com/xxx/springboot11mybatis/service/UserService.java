package com.xxx.springboot11mybatis.service;

import com.xxx.springboot11mybatis.entity.User;

import java.util.List;

public interface UserService {
    int save(User user);

    int update(User user);

    int delete(Long id);

    User getById(Long id);

    List<User> getAll();

    List<User> getByName(User user);
}
