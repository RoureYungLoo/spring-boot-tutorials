package com.xxx.springboot05webmvc.service;

import com.xxx.springboot05webmvc.entity.User;

import java.util.List;

public interface UserService {
    public User findById(Long id);

    public List<User> findList();

    public int save(User user);

    public int updateById(User user);

    public int deleteById(Long id);

}
