package com.xxx.springboot12transaction.service;


import com.xxx.springboot12transaction.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    int save(User user) ;

    int update(User user);

    int delete(Long id);

    User getById(Long id);

    List<User> getAll();

    List<User> getByName(User user);
}
