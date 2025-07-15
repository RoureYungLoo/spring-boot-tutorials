package com.xxx.springboot05webmvc.repository;

import com.xxx.springboot05webmvc.entity.User;

import java.util.List;

public interface UserRepository {
    List<User> selectALl();

    User selectById(Long id);

    int insert(User user);

    int updateById(User user);

    int deleteById(Long id);
}
