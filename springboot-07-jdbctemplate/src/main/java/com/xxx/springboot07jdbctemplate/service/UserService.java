package com.xxx.springboot07jdbctemplate.service;

import com.xxx.springboot07jdbctemplate.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    @Transactional(

    )
    boolean save(User user);

    boolean update(User user);

    boolean delete(Long id);

    User getUserById(Long id);

    List<User> findAll();
}
