package com.xxx.springboot07jdbctemplate.service.impl;

import com.xxx.springboot07jdbctemplate.entity.User;
import com.xxx.springboot07jdbctemplate.repository.UserRepository;
import com.xxx.springboot07jdbctemplate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean save(User user) {
        return userRepository.save(user) > 0;
    }

    @Override
    public boolean update(User user) {
        return userRepository.update(user) > 0;
    }

    @Override
    public boolean delete(Long id) {
        return userRepository.delete(id) > 0;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
