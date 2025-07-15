package com.xxx.springboot05webmvc.service.impl;

import com.xxx.springboot05webmvc.entity.User;
import com.xxx.springboot05webmvc.repository.UserRepository;
import com.xxx.springboot05webmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> findList() {
        return Collections.emptyList();
    }

    @Override
    public int save(User user) {
        return userRepository.insert(user);
    }

    @Override
    public int updateById(User user) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }
}
