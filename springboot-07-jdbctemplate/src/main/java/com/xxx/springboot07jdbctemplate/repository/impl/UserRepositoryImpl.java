package com.xxx.springboot07jdbctemplate.repository.impl;

import com.xxx.springboot07jdbctemplate.entity.User;
import com.xxx.springboot07jdbctemplate.repository.UserRepository;
import org.apache.logging.log4j.message.ReusableMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final Logger log = LoggerFactory.getLogger(UserRepositoryImpl.class);

    /* 注入jdbcTemplate */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(User user) {
        Object[] args = new Object[]{
                user.getName(),
                user.getAge(),
                user.getAddress()
        };
        return jdbcTemplate.update(
                "insert into tb_user(name, age, address,create_time,update_time) values(?,?,?,now(),now())",
                args
        );
    }

    @Override
    public int update(User user) {
        String sql = "update tb_user set name=?, age=?, address=? where id=?";
        Object[] args = new Object[]{
                user.getName(),
                user.getAge(),
                user.getAddress(),
                user.getId(),
        };
        return jdbcTemplate.update(sql, args);
    }

    @Override
    public int delete(Long id) {
        String sql = "delete from tb_user where id=?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public User findById(Long id) {
        String sql = "select * from tb_user where id=?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
//        return jdbcTemplate.queryForObject(sql, rowMapper, id);
        List<User> userList = jdbcTemplate.query(sql, rowMapper, id); // fix: expected 1, actual 0
        if (userList.isEmpty()) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from tb_user";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.query(sql, rowMapper);
    }
}
