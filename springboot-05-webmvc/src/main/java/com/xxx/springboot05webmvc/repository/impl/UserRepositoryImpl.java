package com.xxx.springboot05webmvc.repository.impl;

import com.xxx.springboot05webmvc.entity.User;
import com.xxx.springboot05webmvc.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> selectALl() {
        return Collections.emptyList();
    }

    @Override
    public User selectById(Long id) {
        return null;
    }

    @Override
    public int insert(User user) {
        Object[] args = new Object[]{
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getPhone(),
                user.getNickname(),
                user.getAvatar(),
                user.getGender(),
                user.getBirthday(),
                user.getEnabled(),
                user.getLocked(),
                user.getLastLoginTime(),
                user.getCreateTime(),
                user.getUpdateTime(),
                user.getCreateBy(),
                user.getUpdateBy()
        };
        return jdbcTemplate.update("insert into test_tb_user" +
                "(username, password, email, phone, nickname, avatar, gender, birthday, enabled, locked, last_login_time, create_time, update_time, create_by, update_by) " +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", args);
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
