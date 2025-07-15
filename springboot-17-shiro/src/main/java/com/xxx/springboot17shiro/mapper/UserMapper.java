package com.xxx.springboot17shiro.mapper;

import com.xxx.springboot17shiro.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface UserMapper {
    int saveUser(User user);

    int delete(int id);

    int updateUser(User user);

    User getById(int id);

    List<User> getAll();

    User getByName(String username);

    Set<String> getRoles(String username);

    Set<String> getPerms(String username);
}
